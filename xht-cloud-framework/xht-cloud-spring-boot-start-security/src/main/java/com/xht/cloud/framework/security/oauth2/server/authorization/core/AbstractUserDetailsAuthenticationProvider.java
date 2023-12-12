package com.xht.cloud.framework.security.oauth2.server.authorization.core;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.security.crypto.password.CustomPasswordEncoder;
import com.xht.cloud.framework.security.support.OAuth2EndpointUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 描述 ：抽象的用户认证Provider
 * 提取公共的用户通用基类，方便涉及用户校验的自定义认证模式使用
 *
 * @author : 小糊涂
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 **/
@Getter
@Slf4j
public abstract class AbstractUserDetailsAuthenticationProvider extends AbstractAuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final OAuth2AuthorizationService authorizationService;

    private final PasswordEncoder passwordEncoder = new CustomPasswordEncoder();

    private final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public AbstractUserDetailsAuthenticationProvider(OAuth2AuthorizationService authorizationService, UserDetailsService userDetailsService) {
        Assert.notNull(authorizationService, "authorizationService is not null");
        Assert.notNull(userDetailsService, "userDetailsService is not null");
        this.authorizationService = authorizationService;
        this.userDetailsService = userDetailsService;
    }


    /**
     * 查询数据库 并验证身份信息返回
     *
     * @param additionalParameters
     * @param registeredClientId
     * @return
     * @throws AuthenticationException
     */
    protected Authentication getUsernamePasswordAuthentication(Map<String, Object> additionalParameters, String registeredClientId) throws AuthenticationException {
        Authentication authentication = null;
        try {
            authentication = authenticateUserDetails(additionalParameters, registeredClientId);
        } catch (AccountStatusException ase) {
            String exceptionName = ase.getClass().getSimpleName();
            OAuth2EndpointUtils.throwError(
                    exceptionName,
                    ase.getMessage(),
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        } catch (BadCredentialsException bce) {
            OAuth2EndpointUtils.throwError(
                   "BadCredentialsException",
                    bce.getMessage(),
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        } catch (UsernameNotFoundException unfe) {
            OAuth2EndpointUtils.throwError(
                    "UsernameNotFoundException",
                    unfe.getMessage(),
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }
        return authentication;
    }


    /**
     * 根据查询出的身份信息来进行第二部校验
     *
     * @param userDetails          身份信息
     * @param additionalParameters
     * @throws AuthenticationException
     */
    protected abstract void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException;

    /**
     * 查询数据库获取身份认证信息
     *
     * @param additionalParameters
     * @return {@link UserDetails } 身份信息
     * @throws AuthenticationException
     */
    protected abstract UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException;

    /**
     * 使用 UserDetailsService验证请求信息并返回身份信息
     *
     * @param additionalParameters 请求信息
     * @param registeredClientId   客户端id
     * @return 身份验证信息
     * @throws AuthenticationException
     */
    private Authentication authenticateUserDetails(Map<String, Object> additionalParameters, String registeredClientId) throws AuthenticationException {
        UserDetails user = retrieveUser(additionalParameters);
        if (!user.isAccountNonLocked()) {
            log.error("[用户校验] |-由于用户帐户被锁定，身份验证失败");
            throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "账号锁定"));
        }
        if (!user.isEnabled()) {
            log.error("[用户校验] |- 由于用户帐户被禁用，身份验证失败");
            throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "账号禁用"));
        }
        if (!user.isAccountNonExpired()) {
            log.error("[用户校验] |- 由于用户帐户已过期，身份验证失败");
            throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "账号过期"));
        }
        additionalAuthenticationChecks(user, additionalParameters);
        if (!user.isCredentialsNonExpired()) {
            log.error("[用户校验] |- 由于用户帐户凭据已过期，身份验证失败");
            throw new CredentialsExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "账号凭证过期"));
        }
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    /**
     * 校验scope
     * @param requestedScopes 请求的scope
     * @param registeredClient 客户端信息
     * @return 校验过后的scope
     */
    protected Set<String> validateScopes(Set<String> requestedScopes, RegisteredClient registeredClient) {
        Set<String> authorizedScopes = Collections.emptySet();
        if (!CollectionUtils.isEmpty(requestedScopes)) {
            for (String requestedScope : requestedScopes) {
                if (!registeredClient.getScopes().contains(requestedScope)) {
                  OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_SCOPE,"授权范围无效!");
                }
            }
            authorizedScopes = new LinkedHashSet<>(requestedScopes);
        }
        return authorizedScopes;
    }
}
