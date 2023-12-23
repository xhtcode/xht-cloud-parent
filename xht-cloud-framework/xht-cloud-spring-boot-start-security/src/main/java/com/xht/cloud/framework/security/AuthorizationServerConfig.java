package com.xht.cloud.framework.security;

import com.xht.cloud.framework.security.api.UserInfoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@RefreshScope
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    @Bean
    public UserInfoApi userRestClient() {
        System.out.println("http://127.0.0.1:80/admin");
        WebClient client = WebClient.builder()
                .baseUrl("http://127.0.0.1:80/admin")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build();
        return factory.createClient(UserInfoApi.class);
    }
}
