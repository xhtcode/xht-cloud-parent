package com.xht.cloud.framework.safety.repeat.dao;

import com.xht.cloud.framework.redis.key.RedisKeyTool;
import com.xht.cloud.framework.safety.repeat.RepeatSubmitProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@RequiredArgsConstructor
public class RepeatSubmitDao {

    private final RepeatSubmitProperties properties;

    private final RedisTemplate<String, Object> redisTemplate;


    public Boolean limitLock(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(RedisKeyTool.createName(properties.getPrefix(), key), Instant.now(), timeout, timeUnit);
    }

}
