package com.xht.cloud.framework.safety.limit;

import com.xht.cloud.framework.safety.limit.aop.RequestLimitAop;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 描述 ：接口限流
 *
 * @author : 小糊涂
 **/
@AutoConfiguration
@EnableConfigurationProperties(RequestLimitProperties.class)
public class RequestLimitAutoConfiguration {

    @Bean
    @ConditionalOnBean(StringRedisTemplate.class)
    public RequestLimitAop requestLimitAop(StringRedisTemplate stringRedisTemplate) {
        return new RequestLimitAop(stringRedisTemplate);
    }

}
