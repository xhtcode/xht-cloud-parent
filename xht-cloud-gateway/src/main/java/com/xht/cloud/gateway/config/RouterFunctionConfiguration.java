package com.xht.cloud.gateway.config;

import com.xht.cloud.gateway.handler.ValidateCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * 描述 ：路由信息配置
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Configuration
@RequiredArgsConstructor
public class RouterFunctionConfiguration {

    private final ValidateCodeHandler validateCodeHandler;

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), validateCodeHandler);
    }
}
