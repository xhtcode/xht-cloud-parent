package com.xht.cloud.framework.web.handler;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.GlobalException;
import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.exception.enums.GlobalErrorStatusCode;
import com.xht.cloud.framework.exception.system.SysException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 描述 ：全局异常处理,如果要使用则继承它，注入到spring容器中。
 *
 * @author : 小糊涂
 * @see com.xht.cloud.framework.exception.GlobalException
 * @see SysException
 * @see BizException
 **/
@Slf4j
@RestControllerAdvice
public class DefaultGlobalExceptionHandler implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public DefaultGlobalExceptionHandler() {
        log.debug("自定义 Controller 全局 异常 拦截......");
    }

    /**
     * 捕获 {@code Exception} 异常
     */
    @ExceptionHandler(value = Exception.class)
    public R<String> handle(Exception e) {
        log.error("未知异常: {}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    /**
     * 捕获 {@code Throwable} 异常
     */
    @ExceptionHandler(Throwable.class)
    public R<String> handleThrowable(Throwable e) {
        log.error("未知Throwable异常: {}", e.getMessage(), e);
        return R.failed(ExceptionUtil.getSimpleMessage(e));
    }

    /**
     * 捕获 {@code GlobalException} , {@code SysException} , {@code BizException} 异常
     */
    @ExceptionHandler(value = {GlobalException.class, SysException.class, BizException.class})
    public R<String> handle(GlobalException e) {
        log.error("系统异常: code={} message={}", e.getCode(), e.getMessage(), e);
        return R.failed(e.getCode(), e.getMessage());
    }


    /**
     * 捕获 {@code NoHandlerFoundException} 异常
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R<String> handle(NoHandlerFoundException e, HttpServletRequest request) {
        log.error(" {} 请求404: {}", request.getRequestURI(), e.getMessage());
        return R.failed(GlobalErrorStatusCode.NOT_FONT_PAGE, e.getMessage());
    }


    /**
     * 捕获 {@code HttpRequestMethodNotSupportedException} 异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式不支持: {}", e.getMessage(), e);
        return R.failed(GlobalErrorStatusCode.NO_REQUEST_METHOD, ExceptionUtil.getSimpleMessage(e));
    }

    /**
     * jsr 303校验异常捕获
     *
     * @param e       BindException
     * @param request HttpServletRequest
     * @return Result
     */
    @ExceptionHandler(BindException.class)
    public R<Map<String, Object>> handleException(BindException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        BindingResult bindingResult = e.getBindingResult();
        Map<String, Object> resultMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            if (!CollectionUtils.isEmpty(fieldErrors)) {
                for (FieldError fieldError : fieldErrors) {
                    resultMap.put(fieldError.getField(), StringUtils.emptyDefault(fieldError.getDefaultMessage(), GlobalErrorStatusCode.VALIDATE_FAILED.desc()));
                }
            }
        }
        log.error("请求地址:{}参数检验失败,请求方式：{} ,data={}", requestURI, request.getMethod(), resultMap);
        return R.failed(GlobalErrorStatusCode.VALIDATE_FAILED, resultMap);
    }

}
