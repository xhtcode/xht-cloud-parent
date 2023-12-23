package com.xht.cloud.framework.rpc.configuration;

import com.xht.cloud.framework.rpc.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Configuration(proxyBeanMethods = false)
public class FeignConfiguration {

    /**
     * Feign Logger 配置
     * <p>
     * 1. NONE（默认） --- 不记录任何日志
     * 2. BASIC ---	仅记录请求方法，URL，响应状态代码以及执行时间（适合生产环境）
     * 3. HEADERS --- 记录BASIC级别的基础上，记录请求和响应的header
     * 4. FULL --- 记录请求和响应header，body和元数据
     *
     * @return feign 日志级别
     */
    @Bean
    public feign.Logger.Level logger() {
        return feign.Logger.Level.BASIC;
    }

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
