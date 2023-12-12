package com.xht.cloud.framework.security;

import com.xht.cloud.framework.security.api.UserInfoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@RefreshScope
@AutoConfiguration
@RequiredArgsConstructor
public class WebClientConfig {

    private final Oauth2Properties oauth2Properties;

    @Bean
    public UserInfoApi userRestClient() {
        WebClient client = WebClient.builder()
                .baseUrl(oauth2Properties.getClientServerUrl())
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build();
        return factory.createClient(UserInfoApi.class);
    }
}
