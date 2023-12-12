package com.xht.cloud.framework.security.oauth2.server.authorization.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述 ：抽象的认证Provider
 * 提取公共的通用认证基类，方便设置返回Token的信息设置
 *
 * @author : 小糊涂
 **/
@Slf4j
public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {

    protected abstract String getErrorUri();

    /**
     * 生成 Access token
     *
     * @param tokenContextBuilder  oauth2token 构建
     * @param authorizationBuilder oauth2 认证构建 {@link OAuth2Authorization.Builder}
     * @param tokenGenerator       token 生成策略{@link OAuth2TokenGenerator}
     * @return {@link OAuth2AccessToken}
     */
    protected final OAuth2AccessToken createOAuth2AccessToken(DefaultOAuth2TokenContext.Builder tokenContextBuilder, OAuth2Authorization.Builder authorizationBuilder, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
        // ----- Access token -----
        OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
        OAuth2Token generatedAccessToken = tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                    "The token generator failed to generate the access token.", getErrorUri());
            throw new OAuth2AuthenticationException(error);
        }
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());
        if (generatedAccessToken instanceof ClaimAccessor) {
            authorizationBuilder.id(accessToken.getTokenValue())
                    .token(accessToken,
                            (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME,
                                    ((ClaimAccessor) generatedAccessToken).getClaims()));
        } else {
            authorizationBuilder.id(accessToken.getTokenValue()).accessToken(accessToken);
        }
        return accessToken;
    }

    /**
     * 生成 Refresh token
     *
     * @param tokenContextBuilder  oauth2token 构建
     * @param authorizationBuilder oauth2 认证构建 {@link OAuth2Authorization.Builder}
     * @param tokenGenerator       token 生成策略{@link OAuth2TokenGenerator}
     * @param clientPrincipal
     * @param registeredClient
     * @return {@link OAuth2RefreshToken}
     */
    protected OAuth2RefreshToken creatOAuth2RefreshToken(DefaultOAuth2TokenContext.Builder tokenContextBuilder, OAuth2Authorization.Builder authorizationBuilder, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, OAuth2ClientAuthenticationToken clientPrincipal, RegisteredClient registeredClient) {
        // ----- Refresh token -----
        OAuth2RefreshToken refreshToken = null;
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
                // Do not issue refresh token to public client
                !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
            OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = tokenGenerator.generate(tokenContext);
            if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate the refresh token.", getErrorUri());
                throw new OAuth2AuthenticationException(error);
            }
            refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
            authorizationBuilder.refreshToken(refreshToken);
        }
        return refreshToken;
    }

    /**
     * 创建oidc Token
     *
     * @param principal            身份验证信息
     * @param sessionRegistry      用于跟踪和管理用户会话。它提供了一种机制来管理在应用程序中的活动会话。
     * @param tokenContextBuilder  oauth2token 构建
     * @param authorizationBuilder oauth2 认证构建 {@link OAuth2Authorization.Builder}
     * @param tokenGenerator       token 生成策略{@link OAuth2TokenGenerator}
     * @param requestedScopes      范围
     * @return {@link OidcIdToken}
     */
    protected OidcIdToken createOidcIdToken(Authentication principal, SessionRegistry sessionRegistry, DefaultOAuth2TokenContext.Builder tokenContextBuilder, OAuth2Authorization.Builder authorizationBuilder, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, Set<String> requestedScopes) {
        return null;
    }

    /**
     * 添加oidc的参数，如果没有就返回一个空的map
     *
     * @param idToken {@link OidcIdToken}
     * @return {@link Map<String, Object>}
     */
    protected Map<String, Object> idTokenAdditionalParameters(OidcIdToken idToken) {
        Map<String, Object> additionalParameters = Collections.emptyMap();
        if (idToken != null) {
            additionalParameters = new HashMap<>();
            additionalParameters.put(OidcParameterNames.ID_TOKEN, idToken.getTokenValue());
        }
        return additionalParameters;
    }

}
