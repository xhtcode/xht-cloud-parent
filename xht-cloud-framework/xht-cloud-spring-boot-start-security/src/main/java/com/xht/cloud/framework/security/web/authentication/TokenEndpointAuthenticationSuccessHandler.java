package com.xht.cloud.framework.security.web.authentication;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.security.support.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 ：token生成成功 响应信息处理
 * 在 使用授权码授权流程或其他授权流程获取访问令牌时，授权服务器会返回一个包含访问令牌、令牌类型、过期时间等信息的响应。
 *
 * @author : 小糊涂
 * @see org.springframework.security.oauth2.server.authorization.web.OAuth2TokenEndpointFilter
 **/
@Slf4j
public class TokenEndpointAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public TokenEndpointAuthenticationSuccessHandler() {
        log.info("token 端点 当身份验证尝试成功时调用");
    }

    /**
     * 访问令牌 响应处理程序。
     *
     * @param request        {@link HttpServletRequest}
     * @param response       {@link HttpServletResponse}
     * @param authentication 认证过程中创建的{@link Authentication}对象
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Map<String, Object> tokenMap = new HashMap<>();
        OAuth2AccessTokenAuthenticationToken accessTokenAuthentication = (OAuth2AccessTokenAuthenticationToken) authentication;
        OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
        OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
        Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();
        tokenMap.put("access_token", accessToken.getTokenValue());
        tokenMap.put("token_type", accessToken.getTokenType().getValue());
        tokenMap.put("scopes", accessToken.getScopes());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            tokenMap.put("expires_in", ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }
        if (Objects.nonNull(refreshToken)) {
            tokenMap.put("refresh_token", refreshToken.getTokenValue());
        }
        if (!CollectionUtils.isEmpty(additionalParameters)) {
            tokenMap.putAll(additionalParameters);
        }
        WebUtil.renderString(response, R.ok(tokenMap));
        log.debug("访问令牌 响应处理: access_token={}", accessToken.getTokenValue());
    }


}
