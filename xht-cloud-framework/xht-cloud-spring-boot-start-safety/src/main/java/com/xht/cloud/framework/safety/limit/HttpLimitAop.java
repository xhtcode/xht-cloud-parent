package com.xht.cloud.framework.safety.limit;

import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.core.support.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Aspect
@RequiredArgsConstructor
public class HttpLimitAop {

    private final StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(com.xht.cloud.framework.safety.limit.HttpLimit)")
    private void check() {
    }

    private DefaultRedisScript<Long> redisScript;

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));
    }

    @Before("check()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //拿到RedisLimit注解，如果存在则说明需要限流
        HttpLimit redisLimit = method.getAnnotation(HttpLimit.class);
        if (redisLimit != null) {
            //获取redis的key
            String key = redisLimit.key();
            String className = method.getDeclaringClass().getName();
            String name = method.getName();
            String limitKey = key + className + method.getName();
            log.info("name is {} limitKey is {}", name, limitKey);
            if (!StringUtils.hasText(key)) {
                throw new BizException("key cannot be null");
            }
            long limit = redisLimit.permitsPerSecond();
            long expire = redisLimit.expire();
            List<String> keys = new ArrayList<>();
            keys.add(key);
            Long count = stringRedisTemplate.execute(redisScript, keys, StringUtils.emptyDefault(limit, "2"), StringUtils.emptyDefault(expire, "60"));
            log.info("Access try count is {} for key={}", count, key);
            if (count != null && count == 0) {
                log.debug("令牌桶={}，获取令牌失败", key);
                throw new BizException(redisLimit.msg());
            }
        }
    }

}
