package com.xht.cloud.framework.security.oauth2.server.authorization.password;

import com.xht.cloud.framework.security.definition.CustomGrantType;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 描述 ：自定义密码模式认证Token
 *
 * @author : 小糊涂
 **/
@Getter
public class OAuth2ResourceOwnerPasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    private final Set<String> scopes;

    private final String username;

    public OAuth2ResourceOwnerPasswordAuthenticationToken(Authentication clientPrincipal,
                                                          @Nullable String username,
                                                          @Nullable Set<String> scopes,
                                                          @Nullable Map<String, Object> additionalParameters) {
        super(CustomGrantType.PASSWORD, clientPrincipal, additionalParameters);
        Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
        Assert.notNull(username, "username cannot be null");
        this.scopes = Collections.unmodifiableSet(!CollectionUtils.isEmpty(scopes) ? new HashSet<>(scopes) : Collections.emptySet());
        this.username = username;
    }

}
