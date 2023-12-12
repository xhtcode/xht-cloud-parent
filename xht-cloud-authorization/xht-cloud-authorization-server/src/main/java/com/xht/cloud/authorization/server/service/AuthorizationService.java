package com.xht.cloud.authorization.server.service;

import com.xht.cloud.authorization.server.dataobject.AuthorizationDO;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public interface AuthorizationService {

    void save(OAuth2Authorization authorization);
    void delete(OAuth2Authorization authorization);

    OAuth2Authorization findByState(String state);

    OAuth2Authorization findByAuthorizationCodeValue(String authorizationCode);

    OAuth2Authorization findByAccessTokenValue(String accessToken);

    OAuth2Authorization findByRefreshTokenValue(String refreshToken);

    OAuth2Authorization findByOidcIdTokenValue(String idToken);

    OAuth2Authorization findByUserCodeValue(String userCode);

    OAuth2Authorization findByDeviceCodeValue(String deviceCode);

    OAuth2Authorization findByAnyTokenType(String token);

    OAuth2Authorization findById(String id);

    AuthorizationDO convert(OAuth2Authorization authorization);
    OAuth2Authorization convert(AuthorizationDO authorizationDO);
}
