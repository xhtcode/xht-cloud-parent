package com.xht.cloud.authorization.server.service;

import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.security.Principal;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public interface AuthorizationConsentService {

    /**
     * Saves the {@link OAuth2AuthorizationConsent}.
     *
     * @param authorizationConsent the {@link OAuth2AuthorizationConsent}
     */
    void save(OAuth2AuthorizationConsent authorizationConsent);

    /**
     * Removes the {@link OAuth2AuthorizationConsent}.
     *
     * @param authorizationConsent the {@link OAuth2AuthorizationConsent}
     */
    void remove(OAuth2AuthorizationConsent authorizationConsent);

    /**
     * Returns the {@link OAuth2AuthorizationConsent} identified by the provided
     * {@code registeredClientId} and {@code principalName}, or {@code null} if not found.
     *
     * @param registeredClientId the identifier for the {@link RegisteredClient}
     * @param principalName the name of the {@link Principal}
     * @return the {@link OAuth2AuthorizationConsent} if found, otherwise {@code null}
     */
    @Nullable
    OAuth2AuthorizationConsent findById(String registeredClientId, String principalName);
}
