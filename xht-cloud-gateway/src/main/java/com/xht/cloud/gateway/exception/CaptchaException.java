package com.xht.cloud.gateway.exception;

import com.xht.cloud.framework.exception.business.BizException;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：验证码异常
 *
 * @author : 小糊涂
 **/
public class CaptchaException extends BizException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
