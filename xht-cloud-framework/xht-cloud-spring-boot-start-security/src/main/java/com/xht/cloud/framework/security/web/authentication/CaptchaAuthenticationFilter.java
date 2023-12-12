package com.xht.cloud.framework.security.web.authentication;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.security.exceptions.InvalidCaptchaException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * 描述 ：自定义验证码拦截器
 *
 * @author : 小糊涂
 **/
@Slf4j
public class CaptchaAuthenticationFilter extends GenericFilterBean {

    private AuthenticationFailureHandler failureHandler;
    private final RequestMatcher requiresAuthenticationRequestMatcher;

    /**
     * 初始化该过滤器，设置拦截的地址
     *
     * @param defaultFilterProcessesUrl 拦截的地址
     */
    public CaptchaAuthenticationFilter(String defaultFilterProcessesUrl) {
        Assert.isFalse(!StringUtils.hasText(defaultFilterProcessesUrl), "拦截的地址 defaultFilterProcessesUrl不能为空");
        requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(defaultFilterProcessesUrl);
        failureHandler = new SimpleUrlAuthenticationFailureHandler(defaultFilterProcessesUrl + "?error");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 检验是否是post请求并且是需要拦截的地址
        if (!this.requiresAuthenticationRequestMatcher.matches(request) || !request.getMethod().equals(HttpMethod.POST.toString())) {
            chain.doFilter(request, response);
            return;
        }
        log.info("开始校验验证码");
        // 获取参数中的验证码
        String code = request.getParameter("code");
        try {
            if (ObjectUtils.isEmpty(code)) {
                throw new InvalidCaptchaException("验证码不能为空！");
            }
            // 获取session中存储的验证码
            Object sessionCaptcha = request.getSession(Boolean.FALSE).getAttribute("captcha");
            if (sessionCaptcha instanceof String sessionCode) {
                if (!sessionCode.equalsIgnoreCase(code)) {
                    throw new InvalidCaptchaException("验证码错误！");
                }
            } else {
                throw new InvalidCaptchaException("验证码不正常，请重新获取!");
            }
        } catch (AuthenticationException ex) {
            this.failureHandler.onAuthenticationFailure(request, response, ex);
            return;
        }
        log.info("验证码校验通过");
        // 验证码校验通过开始执行接下来的逻辑
        chain.doFilter(request, response);
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }


}
