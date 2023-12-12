package com.xht.cloud.framework.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 描述 ：redis 缓存工具类
 *
 * @author : 小糊涂
 **/
@Slf4j
public class RedisServiceImpl implements RedisService {

    private final RedisOperations<String, Object> redisTemplate;

    public RedisServiceImpl(RedisOperations<String, Object> redisTemplate) {
        Assert.notNull(redisTemplate, "[Assertion failed] - this redisTemplate is required; it must not be null");
        this.redisTemplate = redisTemplate;
    }

    /**
     * @param key     redis key
     * @param timeout 超时时常
     * @param unit    超时类型
     * @param data    数据
     * @param <T>
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getKey(String key, long timeout, TimeUnit unit, Supplier<T> data) {
        Object redisData = redisTemplate.opsForValue().get(key);
        if (Objects.nonNull(redisData)) {
            return (T) redisData;
        }
        T t = data.get();
        redisTemplate.opsForValue().set(key, t, timeout, unit);
        return t;
    }

    /**
     * 删除key
     *
     * @param key redis key
     * @return boolean true成功
     */
    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除key
     *
     * @param keys redis key
     * @return boolean true成功
     */
    @Override
    public Long delete(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }
}
