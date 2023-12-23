package com.xht.cloud.framework.security;

import com.xht.cloud.framework.core.constant.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：异常处理,如果要使用则继承它，注入到spring容器中。
 *
 * @author : 小糊涂
 * @see AccessDeniedException
 **/
@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 捕获 AccessDeniedException 并抛出 防止 {@code AuthenticationEntryPoint
     * } 捕获不到异常
     *
     * @param e {@link AccessDeniedException}
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedException(HttpServletRequest request, AccessDeniedException e) throws AccessDeniedException {
        request.setAttribute(CommonConstants.ERROR_MESSAGE, e.getMessage());
        throw e;
    }
}
