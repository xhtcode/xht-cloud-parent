package com.xht.cloud.framework.safety.repeat.dao;

import com.xht.cloud.framework.redis.key.RedisKeyTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RepeatSubmitDao {

    private final RedisTemplate<String, Object> redisTemplate;

    private final static String KEY = "xht:repeat:limit";

    public Boolean limitLock(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(RedisKeyTool.createName(KEY, key), Instant.now(), timeout, timeUnit);
    }

}
