package com.xht.cloud.framework.file.oss.exception;

import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class OssException extends FileException {
    /**
     * @param message 异常描述
     */
    public OssException(String message) {
        super(message);
    }

    /**
     * @param statusCode 业务异常状态码 {@link OssErrorStatusCode}
     */
    public OssException(OssErrorStatusCode statusCode) {
        super(statusCode);
    }

    public OssException(Throwable e) {
        super(null, e.getLocalizedMessage(), e);
    }
}
