package com.xht.cloud.gateway.service.impl;

import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.gateway.exception.CaptchaException;
import com.xht.cloud.gateway.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 ：验证码
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@RefreshScope
@Service
@RequiredArgsConstructor
public class ValidateCodeServiceImpl implements ValidateCodeService {

    private final Producer captchaProducer;

    private final Producer captchaProducerMath;

    @Value("${xht.captchaType:math}")
    private String captchaType;
    @Override
    public R<?> createCaptcha() throws CaptchaException {
        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String capStr, code = null;
        BufferedImage image = null;
        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }
        //redisService.set(verifyKey, code, GlobalRedisEnums.LOGIN_CAPTCHA_KEY.getTimeout(), TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(Objects.requireNonNull(image), "jpg", os);
        } catch (IOException e) {
            return R.failed(e.getMessage());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("key", uuid);
        result.put("img", Base64.getEncoder().encodeToString(os.toByteArray()));
        return R.ok(result);
    }

    @Override
    public void checkCaptcha(String code, String uuid) throws CaptchaException {
        if (!StringUtils.hasText(code)) {
            throw new CaptchaException("验证码不能为空");
        }
        if (!StringUtils.hasText(uuid)) {
            throw new CaptchaException("验证码已失效");
        }
    }
}
