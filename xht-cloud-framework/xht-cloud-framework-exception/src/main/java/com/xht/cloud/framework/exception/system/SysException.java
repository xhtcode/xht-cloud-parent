package com.xht.cloud.framework.exception.system;

import com.xht.cloud.framework.exception.GlobalException;
import com.xht.cloud.framework.exception.IErrorStatusCode;

import java.io.Serial;

/**
 * 描述 ：系统异常
 *
 * @author : 小糊涂
 **/
public class SysException extends GlobalException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param message 异常描述
     */
    public SysException(String message) {
        super(message);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    /**
     * @param statusCode 业务异常状态码 {@link IErrorStatusCode}
     */
    public SysException(IErrorStatusCode statusCode) {
        super(statusCode.code(), statusCode.desc());
    }

    /**
     * @param code    异常状态码
     * @param message 异常描述
     */
    public SysException(Integer code, String message) {
        super(code, message);
    }

    public SysException(String errMessage, Throwable e) {
        super(IErrorStatusCode.DEFAULT_ERR_CODE, errMessage, e);
    }

    public SysException(Integer errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
