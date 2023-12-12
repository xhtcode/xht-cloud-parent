package com.xht.cloud.framework.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * 描述 ：验证码异常
 *
 * @author : 小糊涂
 **/
public class InvalidCaptchaException extends AuthenticationException {

    /**
     * @param message 异常描述
     */
    public InvalidCaptchaException(String message) {
        super(message);
    }
}
