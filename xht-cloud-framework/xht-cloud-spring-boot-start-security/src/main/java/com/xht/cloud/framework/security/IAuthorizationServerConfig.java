package com.xht.cloud.framework.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 描述 ：OAuth2 协议端点配置接口类
 * <p>
 * <a href=''></a>
 *
 * @author : 小糊涂
 * @see <a target="_blank"
 * href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html#defining-required-components">
 * 参考文档</a>
 **/
@SuppressWarnings("all")
public abstract class IAuthorizationServerConfig {

    /**
     * (授权服务器设置) 是用于管理身份验证和授权流程的配置信息。
     * 授权服务器是一个中央身份验证服务，用于颁发和管理访问令牌，以便客户端应用程序可以向受保护的资源发出请求。
     *
     * @return {@link AuthorizationServerSettings}
     */
    protected abstract AuthorizationServerSettings authorizationServerSettings();

    /**
     * oauth2 个性化配置
     * 协议端点的 Spring 安全过滤器链。
     *
     * @param http                        {@link HttpSecurity }
     * @param registeredClientRepository  客户端存储 {@link RegisteredClientRepository}
     * @param authorizationService        授权信息存储 {@link OAuth2AuthorizationService}
     * @param authorizationConsentService 授权许可存储 {@link OAuth2AuthorizationConsentService}
     * @param authorizationServerSettings 授权服务器设置 {@link AuthorizationServerSettings}
     * @return {@link SecurityFilterChain}
     */
    protected abstract SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http, RegisteredClientRepository registeredClientRepository, OAuth2AuthorizationService authorizationService, OAuth2AuthorizationConsentService authorizationConsentService, AuthorizationServerSettings authorizationServerSettings) throws Exception;

    /**
     * token自定义配置
     *
     * @return {@link OAuth2TokenGenerator}
     */
    protected abstract OAuth2TokenGenerator<?> tokenGenerator();
}
