package com.xht.cloud.framework.security.web;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.exception.enums.UserErrorStatusCode;
import com.xht.cloud.framework.security.support.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import java.io.IOException;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
public class ResourceServerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Commences an authentication scheme.
     * <p>
     * <code>ExceptionTranslationFilter</code> will populate the <code>HttpSession</code>
     * attribute named
     * <code>AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY</code>
     * with the requested target URL before calling this method.
     * <p>
     * Implementations should modify the headers on the <code>ServletResponse</code> as
     * necessary to commence the authentication process.
     *
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("【资源服务器】AccessDeniedHandler accessDeniedException {}", authException.getMessage(), authException);
        response.setStatus(410);
        R<Object> result = R.failed(UserErrorStatusCode.NOT_LOGIN, authException.getMessage());
        if (authException instanceof InvalidBearerTokenException) {
            result = R.failed(UserErrorStatusCode.BAD_TOKEN);
            result.setMsg("token 令牌已过期！");
        } else if (authException instanceof BadCredentialsException || authException instanceof CredentialsExpiredException) {
            result = R.failed(UserErrorStatusCode.BAD_PASSWORD);
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = R.failed(UserErrorStatusCode.BAD_TOKEN);
            result.setMsg("请求没有携带有效的令牌（Token），或者令牌无效或过期！");
        } else if (authException instanceof UsernameNotFoundException) {
            result = R.failed(UserErrorStatusCode.USER_NOT_EXIST, "用户名不存在！");
            result.setMsg("用户名不存在！");
        } else if (authException instanceof PreAuthenticatedCredentialsNotFoundException) {
            result = R.failed(UserErrorStatusCode.BAD_AUTHENTICATED);
        } else if (authException instanceof LockedException) {
            result = R.failed(UserErrorStatusCode.USER_LOCK);
        } else if (authException instanceof DisabledException) {
            result = R.failed(UserErrorStatusCode.USER_FORBIDDEN);
        } else if (authException instanceof AccountExpiredException) {
            result = R.failed(UserErrorStatusCode.USER_EXPIRED);
        } else if (authException instanceof AccountStatusException) {
            result = R.failed(UserErrorStatusCode.USER_STATUS_ERROR);
        }
        WebUtil.renderString(response, result);
    }
}
