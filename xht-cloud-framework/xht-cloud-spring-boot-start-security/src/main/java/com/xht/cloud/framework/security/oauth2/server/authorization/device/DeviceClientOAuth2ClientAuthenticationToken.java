package com.xht.cloud.framework.security.oauth2.server.authorization.device;

import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Map;

/**
 * 描述 ：设备码模式token
 *
 * @author : 小糊涂
 **/
public class DeviceClientOAuth2ClientAuthenticationToken extends OAuth2ClientAuthenticationToken {

    public DeviceClientOAuth2ClientAuthenticationToken(String clientId, ClientAuthenticationMethod clientAuthenticationMethod,
                                                       @Nullable Object credentials, @Nullable Map<String, Object> additionalParameters) {
        super(clientId, clientAuthenticationMethod, credentials, additionalParameters);
    }

    public DeviceClientOAuth2ClientAuthenticationToken(RegisteredClient registeredClient, ClientAuthenticationMethod clientAuthenticationMethod,
                                                       @Nullable Object credentials) {
        super(registeredClient, clientAuthenticationMethod, credentials);
    }

}
