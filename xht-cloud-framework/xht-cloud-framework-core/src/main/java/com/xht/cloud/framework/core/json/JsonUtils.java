package com.xht.cloud.framework.core.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xht.cloud.framework.exception.system.SysException;
import com.xht.cloud.framework.core.support.StringUtils;

import java.util.Objects;

/**
 * 描述 ：json工具类
 *
 * @author : 小糊涂
 **/
public final class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModules(new CustomJavaTimeModule());
    }

    /**
     * Object 转化成 json字符串
     */
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new SysException("系统异常 json 序列化有问题!");
        }
    }

    /**
     * 将json形式的字符串数据转换成单个对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String json, Class<?> clazz) {
        if (StringUtils.hasText(json) && Objects.nonNull(clazz)) {
            try {
                if (Objects.equals(String.class, clazz)) {
                    return (T) json;
                }
                return (T) objectMapper.readValue(json, clazz);
            } catch (Exception e) {
                throw new SysException("系统异常 json 序列化有问题!");
            }
        }
        return null;
    }

    /**
     * 将json形式的字符串数据转换成多个对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        if (StringUtils.hasText(json) && Objects.nonNull(typeReference)) {
            try {
                if (typeReference.getType().equals(String.class)) {
                    return (T) json;
                }
                return (T) objectMapper.readValue(json, typeReference);
            } catch (Exception e) {
                throw new SysException("系统异常 json 序列化有问题!");
            }
        }

        return null;
    }
}
