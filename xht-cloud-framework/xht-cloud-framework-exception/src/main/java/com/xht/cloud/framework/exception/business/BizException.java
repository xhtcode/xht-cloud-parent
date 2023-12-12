package com.xht.cloud.framework.exception.business;


import com.xht.cloud.framework.exception.GlobalException;
import com.xht.cloud.framework.exception.IErrorStatusCode;

import java.io.Serial;

/**
 * 描述 ： 业务异常
 *
 * @author : 小糊涂
 **/
public class BizException extends GlobalException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param message 异常描述
     */
    public BizException(String message) {
        super(message);
    }
    public BizException(Throwable cause) {
        super(cause);
    }

    /**
     * @param code    异常状态码
     * @param message 异常描述
     */
    public BizException(Integer code, String message) {
        super(code, message);
    }

    /**
     * @param statusCode 业务异常状态码 {@link IErrorStatusCode}
     */
    public BizException(IErrorStatusCode statusCode) {
        super(statusCode.code(), statusCode.desc());
    }

    public BizException(Integer errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
