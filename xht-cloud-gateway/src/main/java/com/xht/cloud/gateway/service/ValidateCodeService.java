package com.xht.cloud.gateway.service;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.gateway.exception.CaptchaException;

import java.io.IOException;

/**
 * 描述 ：验证码
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
public interface ValidateCodeService {

    /**
     * 生成验证码
     */
    R<?> createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    void checkCaptcha(String key, String uuid) throws CaptchaException;

}
