package com.xht.cloud.framework.security;

import cn.hutool.core.util.ArrayUtil;
import com.xht.cloud.framework.security.web.ResourceServerAuthenticationEntryPoint;
import com.xht.cloud.framework.security.web.access.ResourceAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.CollectionUtils;

/**
 * 描述 ：资源服务器 拦截器配置
 *
 * @author : 小糊涂
 **/
@Slf4j
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class ResourceSecurityConfig {

    private final PermitAllUrlProperties permitAllUrlProperties;

    private final OpaqueTokenIntrospector opaqueTokenIntrospector;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(matcherRegistry -> {
                    // 所有请求都进行拦截
                    if (!CollectionUtils.isEmpty(permitAllUrlProperties.getGetUrls())) {
                        matcherRegistry.requestMatchers(HttpMethod.GET, ArrayUtil.toArray(permitAllUrlProperties.getGetUrls(), String.class)).permitAll();
                    }
                    if (!CollectionUtils.isEmpty(permitAllUrlProperties.getPostUrls())) {
                        matcherRegistry.requestMatchers(HttpMethod.POST, ArrayUtil.toArray(permitAllUrlProperties.getPostUrls(), String.class)).permitAll();
                    }
                    if (!CollectionUtils.isEmpty(permitAllUrlProperties.getDeleteUrls())) {
                        matcherRegistry.requestMatchers(HttpMethod.DELETE, ArrayUtil.toArray(permitAllUrlProperties.getDeleteUrls(), String.class)).permitAll();
                    }
                    if (!CollectionUtils.isEmpty(permitAllUrlProperties.getPutUrls())) {
                        matcherRegistry.requestMatchers(HttpMethod.PUT, ArrayUtil.toArray(permitAllUrlProperties.getPutUrls(), String.class)).permitAll();
                    }
                    if (!CollectionUtils.isEmpty(permitAllUrlProperties.getAllUrls())) {
                        matcherRegistry.requestMatchers(ArrayUtil.toArray(permitAllUrlProperties.getAllUrls(), String.class)).permitAll();
                    }
                    matcherRegistry.anyRequest().authenticated();
                })
                .oauth2ResourceServer(resourceServer -> {
                    resourceServer.accessDeniedHandler(new ResourceAccessDeniedHandler());
                    resourceServer.authenticationEntryPoint(new ResourceServerAuthenticationEntryPoint());
                    resourceServer.opaqueToken((opaqueTokenCustomizer) -> {
                        opaqueTokenCustomizer.introspector(opaqueTokenIntrospector);
                    });
                })
                .headers(headersConfigurer -> {
                    headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                }) //资源服务器配置
                .csrf(CsrfConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
