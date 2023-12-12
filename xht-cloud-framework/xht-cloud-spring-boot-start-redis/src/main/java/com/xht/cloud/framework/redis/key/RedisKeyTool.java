package com.xht.cloud.framework.redis.key;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ArrayUtil;
import org.springframework.lang.NonNull;

/**
 * 描述 ：redis key 生成
 *
 * @author : 小糊涂
 **/
public final class RedisKeyTool {

    private static final String DEFAULT_CONJUNCTION = ":";

    /**
     * 生成key
     */
    public static String createName(String... key) {
        return ArrayUtil.join(key, DEFAULT_CONJUNCTION);
    }

    /**
     * 格式化 Key
     *
     * @param args 格式化的参数
     * @return Key
     */
    public static String createNameTemplate(@NonNull final String keyTemplate, @NonNull Object... args) {
        return StrFormatter.format(keyTemplate, args);
    }

    /**
     * 生成key
     */
    public static RedisKeyBO createKey(String... key) {
        return new RedisKeyBO(createName(key));
    }

    /**
     * 生成key
     */
    public static RedisKeyBO createKeyTemplate(@NonNull final String keyTemplate, @NonNull Object... args) {
        return new RedisKeyBO(createNameTemplate(keyTemplate, args));
    }

}
