package com.xht.cloud.framework.file;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：oss异常处理,如果要使用则继承它，注入到spring容器中。
 *
 * @author : 小糊涂
 * @see com.xht.cloud.framework.file.oss.exception.OssException
 **/
@Slf4j
@RestControllerAdvice
public class OssExceptionHandler implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 捕获 {@code OssException} 异常
     */
    @ExceptionHandler(value = OssException.class)
    public R<?> handle(OssException e) {
        log.error("OSS异常: code=`{}` message=`{}`", e.getCode(), e.getLocalizedMessage(), e);
        return R.failed(e.getCode(), e.getMessage());
    }
}
