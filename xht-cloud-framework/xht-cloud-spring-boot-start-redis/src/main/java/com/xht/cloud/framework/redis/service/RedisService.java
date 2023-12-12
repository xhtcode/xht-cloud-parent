package com.xht.cloud.framework.redis.service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 描述 ：redis 缓存工具类
 *
 * @author : 小糊涂
 **/
public interface RedisService {

    /**
     * 缓存数据 超时类型是 秒
     *
     * @param key     redis key
     * @param timeout 超时时长
     * @param data    数据
     * @param <T> object
     * @return 数据
     */
    default <T> T getKey(String key, long timeout, Supplier<T> data) {
        return getKey(key, timeout, TimeUnit.SECONDS, data);
    }


    /**
     * 缓存数据
     *
     * @param key     redis key
     * @param timeout 超时时长
     * @param unit    超时类型
     * @param data    数据
     * @param <T>
     * @return 数据
     */
    <T> T getKey(String key, long timeout, TimeUnit unit, Supplier<T> data);

    /**
     * 删除key
     *
     * @param key redis key
     * @return boolean true成功
     */
    boolean delete(String key);

    /**
     * 删除key
     *
     * @param keys redis key
     * @return boolean true成功
     */
    Long delete(Collection<String> keys);
}
