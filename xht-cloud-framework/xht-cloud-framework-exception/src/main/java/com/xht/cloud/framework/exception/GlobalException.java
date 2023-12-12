package com.xht.cloud.framework.exception;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：自定义异常基础类
 *
 * @author : 小糊涂
 **/
@Getter
public class GlobalException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Integer code;

    /**
     * 异常
     * @param cause {@link Throwable}
     */
    public GlobalException(Throwable cause) {
        super(cause);
        this.code = IErrorStatusCode.DEFAULT_ERR_CODE;
    }

    /**
     * @param message 异常描述
     */
    public GlobalException(String message) {
        super(message);
        this.code = IErrorStatusCode.DEFAULT_ERR_CODE;
    }

    /**
     * @param code    异常状态码
     * @param message 异常描述
     */
    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param errMessage 异常描述
     * @param e          {@link Throwable}
     */
    public GlobalException(String errMessage, Throwable e) {
        super(errMessage, e);
        this.code = IErrorStatusCode.DEFAULT_ERR_CODE;
    }

    /**
     * @param errCode    异常状态码
     * @param errMessage 异常描述
     * @param e          {@link    Throwable}
     */
    public GlobalException(Integer errCode, String errMessage, Throwable e) {
        super(errMessage, e);
        this.code = errCode;
    }

}
