package com.xht.cloud.authorization.server.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.xht.cloud.authorization.server.jose.Jwks;
import com.xht.cloud.framework.security.IAuthorizationServerConfig;
import com.xht.cloud.framework.security.oauth2.server.authorization.device.DeviceClientAuthenticationConverter;
import com.xht.cloud.framework.security.oauth2.server.authorization.device.DeviceClientAuthenticationProvider;
import com.xht.cloud.framework.security.oauth2.server.authorization.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import com.xht.cloud.framework.security.oauth2.server.authorization.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import com.xht.cloud.framework.security.oauth2.server.authorization.token.CustomAccessTokenGenerator;
import com.xht.cloud.framework.security.support.OAuth2ConfigurerUtils;
import com.xht.cloud.framework.security.web.authentication.ClientEndpointAuthenticationFailureHandler;
import com.xht.cloud.framework.security.web.authentication.TokenEndpointAuthenticationFailureHandler;
import com.xht.cloud.framework.security.web.authentication.TokenEndpointAuthenticationSuccessHandler;
import com.xht.cloud.framework.security.web.authentication.TokenRevocationAuthenticationFailureHandler;
import com.xht.cloud.framework.security.web.authentication.TokenRevocationAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

/**
 * 描述 ：OAuth2 协议端点配置实现类
 *
 * @author : 小糊涂
 **/
@Slf4j
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class AuthorizationServerConfig extends IAuthorizationServerConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }



    /**
     * (授权服务器设置) 是用于管理身份验证和授权流程的配置信息。
     * 授权服务器是一个中央身份验证服务，用于颁发和管理访问令牌，以便客户端应用程序可以向受保护的资源发出请求。
     *
     * @return {@link AuthorizationServerSettings}
     */
    @Bean
    @Override
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().issuer("http://127.0.0.1:9000").build();
    }

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
    @Bean
    @Order(1)
    @Override
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
                                                                      RegisteredClientRepository registeredClientRepository,
                                                                      OAuth2AuthorizationService authorizationService,
                                                                      OAuth2AuthorizationConsentService authorizationConsentService,
                                                                      AuthorizationServerSettings authorizationServerSettings) throws Exception {
        log.info("加载授权服务器设置\tAuthorizationServiceOAuth2AuthorizationService: {}", authorizationServerSettings.getClass());
        log.info("加载客户端\tRegisteredClientRepository: {}", registeredClientRepository.getClass());
        log.info("加载授权管理\tOAuth2AuthorizationService: {}", authorizationService.getClass());
        log.info("加载授权许可管理\tOAuth2AuthorizationConsentService: {}", authorizationConsentService.getClass());
        // 新建设备码converter和provider
        DeviceClientAuthenticationConverter deviceClientAuthenticationConverter =
                new DeviceClientAuthenticationConverter(
                        authorizationServerSettings.getDeviceAuthorizationEndpoint());
        DeviceClientAuthenticationProvider deviceClientAuthenticationProvider =
                new DeviceClientAuthenticationProvider(registeredClientRepository);
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        OAuth2AuthorizationServerConfigurer serverConfigurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
        // 用于管理新客户和现有客户的RegisteredClientRepository（必需）
        serverConfigurer.registeredClientRepository(registeredClientRepository);
        // 用于管理新的和现有的授权。
        serverConfigurer.authorizationService(authorizationService);
        // 用于管理新的和现有的授权许可
        serverConfigurer.authorizationConsentService(authorizationConsentService);
        // 用于自定义 OAuth2 授权服务器配置设置的AuthorizationServerSettings（必需）
        serverConfigurer.authorizationServerSettings(authorizationServerSettings);
        // 用于生成 OAuth2 授权服务器支持的令牌
        serverConfigurer.tokenGenerator(tokenGenerator());
        // OAuth2 客户端身份验证的配置器
        serverConfigurer.clientAuthentication(clientAuthentication -> {
            // 客户端认证添加设备码的converter和provider
            clientAuthentication.authenticationConverter(deviceClientAuthenticationConverter);
            clientAuthentication.errorResponseHandler(new ClientEndpointAuthenticationFailureHandler());
            clientAuthentication.authenticationProvider(deviceClientAuthenticationProvider);
        });
        // OAuth2 授权端点的配置器 允许您自定义 OAuth2 授权请求的预处理、主处理和后处理逻辑
        serverConfigurer.authorizationEndpoint(authorizationEndpoint -> {
            authorizationEndpoint.consentPage("/oauth2/consent");
        });
        //OAuth2 设备授权端点的配置程序
        serverConfigurer.deviceAuthorizationEndpoint(deviceAuthorizationEndpoint -> {
            deviceAuthorizationEndpoint.verificationUri("/activate");
        });
        //OAuth2 设备验证端点的配置程序
        serverConfigurer.deviceVerificationEndpoint(deviceVerificationEndpoint -> {
            deviceVerificationEndpoint.consentPage("/oauth2/consent");
        });
        // OAuth2 令牌端点的配置器 允许您自定义 OAuth2 访问令牌请求的预处理、主处理和后处理逻辑。
        serverConfigurer.tokenEndpoint(tokenEndpoint -> {
            tokenEndpoint.accessTokenRequestConverter(new OAuth2ResourceOwnerPasswordAuthenticationConverter());
            tokenEndpoint.accessTokenResponseHandler(new TokenEndpointAuthenticationSuccessHandler());
            tokenEndpoint.errorResponseHandler(new TokenEndpointAuthenticationFailureHandler());
        });
        // OAuth2 令牌侦测端点的配置程序。
        serverConfigurer.tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> {
        });
        //OAuth2 令牌注销端点的配置器。
        serverConfigurer.tokenRevocationEndpoint(tokenRevocationEndpoint -> {
            //令牌吊销成功时调用
            tokenRevocationEndpoint.revocationResponseHandler(new TokenRevocationAuthenticationSuccessHandler());
            //用于处理和返回 OAuth2Error 响应的（后处理器）。
            tokenRevocationEndpoint.errorResponseHandler(new TokenRevocationAuthenticationFailureHandler());
        });
        // OAuth2 授权服务器元数据端点的配置器。
        serverConfigurer.authorizationServerMetadataEndpoint(authorizationServerMetadataEndpoint -> {
        });
        // OAuth2 oidc
        serverConfigurer.oidc(oidc -> oidc
                // OpenID Connect 1.0 提供者配置端点的配置器。
                .providerConfigurationEndpoint(providerConfigurationEndpointConfigurer -> {
                })
                //OpenID Connect 1.0 注销端点的配置程序。
                .logoutEndpoint(logoutEndpoint -> {
                })
                // OpenID Connect 1.0 UserInfo 端点的配置器。
                .userInfoEndpoint(userInfoEndpoint -> {
                })
                // OpenID Connect 1.0 客户端注册端点的配置器。
                .clientRegistrationEndpoint(clientRegistrationEndpoint -> {
                })

        );
        //方法进行身份验证时重定向到登录页
        //授权端点
        http.exceptionHandling((exceptions) ->
                exceptions.defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint("/login"),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));
        // 处理使用access token访问用户信息端点和客户端注册端点
        http.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = OAuth2ConfigurerUtils.getTokenGenerator(http);
        http.authenticationProvider(new OAuth2ResourceOwnerPasswordAuthenticationProvider(authorizationService, userDetailsService, tokenGenerator));
        return http.build();
    }

    /**
     * token自定义配置
     *
     * @return {@link OAuth2TokenGenerator}
     */
    @Override
    public OAuth2TokenGenerator<?> tokenGenerator() {
        CustomAccessTokenGenerator accessTokenGenerator = new CustomAccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, refreshTokenGenerator);
    }
}
