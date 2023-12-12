package com.xht.cloud.framework.security.oauth2.server.authorization.password;

import com.xht.cloud.framework.security.definition.CustomGrantType;
import com.xht.cloud.framework.security.support.OAuth2EndpointUtils;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 描述 ：自定义密码模式认证转换器
 *
 * @author : 小糊涂
 **/
@Slf4j
public class OAuth2ResourceOwnerPasswordAuthenticationConverter implements AuthenticationConverter {

    /**
     * @param request {@link HttpServletRequest}
     * @return {@link OAuth2ResourceOwnerPasswordAuthenticationToken}
     */
    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!CustomGrantType.PASSWORD.getValue().equals(grantType)) {
            return null;
        }
        Authentication clientPrincipal = SecurityContextUtil.getAuthentication().orElse(null);

        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);
        // scope (OPTIONAL)
        String scope = OAuth2EndpointUtils.checkOptionalParameter(parameters, OAuth2ParameterNames.SCOPE);
        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, ",")));
        }
        // username (REQUIRED)
        String username = OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.USERNAME);
        // password (REQUIRED)
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.PASSWORD);
        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) && !key.equals(OAuth2ParameterNames.SCOPE)) {
                additionalParameters.put(key, value.size() == 1 ? value.get(0) : value);
            }
        });
        return new OAuth2ResourceOwnerPasswordAuthenticationToken(clientPrincipal, username, requestedScopes, additionalParameters);
    }


}
