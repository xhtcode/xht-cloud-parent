package com.xht.cloud.framework.security.oauth2.server.authorization.password;

import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import com.xht.cloud.framework.security.definition.CustomGrantType;
import com.xht.cloud.framework.security.oauth2.server.authorization.core.AbstractUserDetailsAuthenticationProvider;
import com.xht.cloud.framework.security.support.OAuth2ConfigurerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 描述 ：自定义 OAuth2 密码模式认证 Provider
 *
 * @author : 小糊涂
 **/
@Slf4j
public class OAuth2ResourceOwnerPasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    public OAuth2ResourceOwnerPasswordAuthenticationProvider(OAuth2AuthorizationService authorizationService,
                                                             UserDetailsService userDetailsService,
                                                             OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
        super(authorizationService, userDetailsService);
        Assert.notNull(tokenGenerator, "tokenGenerator is not null");
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2ResourceOwnerPasswordAuthenticationToken resourceOwnerPasswordAuthentication = (OAuth2ResourceOwnerPasswordAuthenticationToken) authentication;
        OAuth2ClientAuthenticationToken clientAuthenticationToken = OAuth2ConfigurerUtils.getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);
        RegisteredClient registeredClient = clientAuthenticationToken.getRegisteredClient();
        if (Objects.isNull(registeredClient) || !registeredClient.getAuthorizationGrantTypes().contains(CustomGrantType.PASSWORD)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }
        Authentication principal = getUsernamePasswordAuthentication(resourceOwnerPasswordAuthentication.getAdditionalParameters(), registeredClient.getId());
        // Default to configured scopes
        Set<String> authorizedScopes = validateScopes(resourceOwnerPasswordAuthentication.getScopes(), registeredClient);
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization
                .withRegisteredClient(registeredClient)
                .principalName(principal.getName())
                .authorizationGrantType(CustomGrantType.PASSWORD)
                .authorizedScopes(authorizedScopes)
                .attribute(Principal.class.getName(), principal);
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(principal).
                authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(authorizedScopes)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrantType(CustomGrantType.PASSWORD)
                .authorizationGrant(resourceOwnerPasswordAuthentication);
        // ----- Access token -----
        OAuth2AccessToken accessToken = createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator);

        // ----- Refresh token -----
        OAuth2RefreshToken refreshToken = creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, clientAuthenticationToken, registeredClient);

        // ----- ID token -----
        OidcIdToken idToken = createOidcIdToken(principal, null, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, resourceOwnerPasswordAuthentication.getScopes());
        authorizationBuilder.id(resourceOwnerPasswordAuthentication.getUsername());
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);

        log.debug("[密码模式 provider] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");

        Map<String, Object> additionalParameters = idTokenAdditionalParameters(idToken);

        return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientAuthenticationToken, accessToken, refreshToken, additionalParameters);
    }


    @Override
    public boolean supports(Class<?> authentication) {
        boolean supports = OAuth2ResourceOwnerPasswordAuthenticationToken.class.isAssignableFrom(authentication);
        log.debug("[密码模式 provider] |- Resource Owner Password Authentication is supports! [{}]", supports);
        return supports;
    }

    /**
     * @return
     */
    @Override
    protected String getErrorUri() {
        return ERROR_URI;
    }

    /**
     * @param userDetails
     * @param additionalParameters
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException {
        String presentedPassword = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);
        if (!this.getPasswordEncoder().matches(String.format("%s%s", presentedPassword, ((CustomUserDetails) userDetails).getPassWordSalt()), userDetails.getPassword())) {
            log.error("[密码模式 用户校验] |- 身份验证失败，因为密码与存储值不匹配");
            throw new BadCredentialsException("用户名不存在或者密码错误!");
        }
    }

    /**
     * @param additionalParameters
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException {
        String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);

        try {
            UserDetailsService enhanceUserDetailsService = getUserDetailsService();
            UserDetails userDetails = enhanceUserDetailsService.loadUserByUsername(username);
            if (Objects.isNull(userDetails)) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return userDetails;
        } catch (UsernameNotFoundException ex) {
            log.error("[密码模式 用户校验] |- 用户名称不存在 ：[{}]", username);
            throw new UsernameNotFoundException("账号不存在!");
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AuthenticationServiceException(ex.getMessage(), ex);
        }
    }
}
