package com.xht.cloud.framework.security.crypto.password;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 描述 ：密码编码器
 *
 * @author : 小糊涂
 **/
@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        return SmUtil.sm3(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            log.warn("Empty encoded password");
            return false;
        }
        String result = SmUtil.sm3(rawPassword.toString());
        return StrUtil.equals(result, encodedPassword);
    }
}
