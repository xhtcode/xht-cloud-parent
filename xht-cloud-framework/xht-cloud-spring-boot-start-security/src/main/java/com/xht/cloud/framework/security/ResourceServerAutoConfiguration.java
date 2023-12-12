package com.xht.cloud.framework.security;

import com.xht.cloud.framework.security.oauth2.server.resource.introspection.CustomOpaqueTokenIntrospector;
import com.xht.cloud.framework.security.service.PermissionCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Order
@EnableConfigurationProperties({PermitAllUrlProperties.class})
@RequiredArgsConstructor
public class ResourceServerAutoConfiguration {

    private final Oauth2Properties oauth2Properties;
    @Bean
    public OpaqueTokenIntrospector opaqueTokenIntrospector() {
        return new CustomOpaqueTokenIntrospector(new SpringOpaqueTokenIntrospector(oauth2Properties.getIntrospectionUri(), oauth2Properties.getClientId(), oauth2Properties.getClientSecret()));
    }

    @Bean("oauth2")
    public PermissionCheckService permissionCheck() {
        return new PermissionCheckService();
    }
}
