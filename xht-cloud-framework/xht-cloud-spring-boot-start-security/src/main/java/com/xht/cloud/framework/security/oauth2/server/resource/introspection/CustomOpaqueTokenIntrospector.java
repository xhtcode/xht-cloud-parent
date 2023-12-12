package com.xht.cloud.framework.security.oauth2.server.resource.introspection;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;

import java.util.Objects;

/**
 * 描述 ：自定义 token 解析
 *
 * @author : 小糊涂
 **/
@Slf4j
@RequiredArgsConstructor
public class CustomOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final SpringOpaqueTokenIntrospector springOpaqueTokenIntrospector;

    /**
     * 内省并验证给定的令牌，返回其属性。
     * <p>
     * 返回{@link java.util.Map}表明令牌是有效的。
     *
     * @param token 令牌
     * @return 令牌的属性
     */
    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserDetailsService bean = SpringUtil.getBean(UserDetailsService.class);
        log.info("不透明的 token 自定义校验 {} ", StringUtils.trimAllWhitespace(token));
        try {
            OAuth2AuthenticatedPrincipal introspect = springOpaqueTokenIntrospector.introspect(token);
            String userName = introspect.getName();
            Object grantType = introspect.getAttribute(OAuth2ParameterNames.GRANT_TYPE);
            if (Objects.equals(AuthorizationGrantType.CLIENT_CREDENTIALS.getValue(), Convert.toStr(grantType, ""))) {
                return introspect;
            }
            CustomUserDetails userDetails = (CustomUserDetails) bean.loadUserByUsername(userName);
            return Objects.requireNonNullElse(userDetails, introspect);
        } catch (Exception e) {
            log.error("暂未登录 {}", e.getMessage(), e);
            throw new InvalidBearerTokenException(String.format("令牌`%s`不合法", token), e);
        }
    }


}
