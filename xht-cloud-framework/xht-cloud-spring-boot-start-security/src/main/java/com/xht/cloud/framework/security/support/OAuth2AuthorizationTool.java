package com.xht.cloud.framework.security.support;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;

import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public final class OAuth2AuthorizationTool {
    /**
     * 私有化构造器
     */
    private OAuth2AuthorizationTool() {
    }

    /**
     * 获取实例
     */
    public static OAuth2AuthorizationTool getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final OAuth2AuthorizationTool oAuth2AuthorizationTool;

        Instance() {
            oAuth2AuthorizationTool = new OAuth2AuthorizationTool();
        }

        public OAuth2AuthorizationTool getInstance() {
            return oAuth2AuthorizationTool;
        }
    }

    public OAuth2AuthorizationTool isState(OAuth2Authorization authorization, Customizer<Object> customizer) {
        Object state = authorization.getAttribute("state");
        if (Objects.nonNull(state)) {
            customizer.customize(state);
        }
        return this;
    }

    public OAuth2AuthorizationTool isCode(OAuth2Authorization authorization, Customizer<OAuth2Authorization.Token<OAuth2AuthorizationCode>> customizer) {
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization.getToken(OAuth2AuthorizationCode.class);
        if (Objects.nonNull(authorizationCode)) {
            customizer.customize(authorizationCode);
        }
        return this;
    }

    public OAuth2AuthorizationTool isRefreshToken(OAuth2Authorization authorization, Customizer<OAuth2Authorization.Token<OAuth2RefreshToken>> customizer) {
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getRefreshToken();
        if (Objects.nonNull(refreshToken)) {
            customizer.customize(refreshToken);

        }
        return this;
    }

    public OAuth2AuthorizationTool isAccessToken(OAuth2Authorization authorization, Customizer<OAuth2Authorization.Token<OAuth2AccessToken>> customizer) {
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
        if (Objects.nonNull(accessToken)) {
            customizer.customize(accessToken);
        }
        return this;
    }

    public void other(OAuth2Authorization authorization, Customizer<Object> customizer) {
        Object state = authorization.getAttribute("state");
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization.getToken(OAuth2AuthorizationCode.class);
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getRefreshToken();
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
        if (Objects.isNull(state) && Objects.isNull(authorizationCode) && Objects.isNull(refreshToken) && Objects.isNull(accessToken)) {
            customizer.customize(customizer);
        }
    }
}
