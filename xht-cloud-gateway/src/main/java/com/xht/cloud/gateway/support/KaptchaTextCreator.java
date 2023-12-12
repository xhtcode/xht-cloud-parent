package com.xht.cloud.gateway.support;

import com.google.code.kaptcha.text.impl.DefaultTextCreator;

import static com.xht.cloud.framework.core.random.ArithmeticProblemUtil.create;

/**
 * 描述 ：验证码生成器
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
public class KaptchaTextCreator extends DefaultTextCreator {

    @Override
    public String getText() {
        return create();
    }

}
