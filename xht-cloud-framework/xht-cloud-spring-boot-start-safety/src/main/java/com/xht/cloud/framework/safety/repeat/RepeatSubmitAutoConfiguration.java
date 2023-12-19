package com.xht.cloud.framework.safety.repeat;

import com.xht.cloud.framework.safety.repeat.aop.RepeatSubmitAspect;
import com.xht.cloud.framework.safety.repeat.dao.RepeatSubmitDao;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 描述 ：重复提交
 *
 * @author : 小糊涂
 **/
@AutoConfiguration
@EnableConfigurationProperties(RepeatSubmitProperties.class)
public class RepeatSubmitAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public RepeatSubmitDao repeatSubmitDao(RepeatSubmitProperties properties, RedisTemplate<String, Object> redisTemplate) {
        return new RepeatSubmitDao(properties, redisTemplate);
    }

    @Bean
    @ConditionalOnBean(RepeatSubmitDao.class)
    public RepeatSubmitAspect repeatSubmitAspect(RepeatSubmitDao submitDao) {
        return new RepeatSubmitAspect(submitDao);
    }

}
