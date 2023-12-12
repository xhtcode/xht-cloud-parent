package com.xht.cloud.authorization.server.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.xht.cloud.framework.core.api.R;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 ：验证码
 *
 * @author : 小糊涂
 **/
@RestController
public class CaptchaController {

    /**
     * 生成验证码
     */
    @GetMapping("/getCaptcha")
    public R<String> getCaptcha(HttpSession session) {
        // 使用hutool-captcha生成图形验证码
        // 定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 4, 2);
        // 存入session中
        session.setAttribute("captcha", captcha.getCode());
        return R.ok(captcha.getImageBase64Data());
    }

    /**
     * 模拟获取手机验证码
     *
     * @param phone
     * @param session
     * @return
     */
    @GetMapping("/getSmsCaptcha")
    public R<String> getSmsCaptcha(String phone, HttpSession session) {
        // 存入session中
        session.setAttribute(phone, "1234");
        return R.ok("获取短信验证码成功", "1234");
    }


}

