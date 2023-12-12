package com.xht.cloud.authorization.server.config;

import com.xht.cloud.framework.security.IdentityAuthenticationConfig;
import com.xht.cloud.framework.security.web.authentication.CaptchaAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 描述 ：用于身份验证的 Spring 安全性筛选器链。
 *
 * @author : 小糊涂
 **/

@Slf4j
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class WebSecurityConfig extends IdentityAuthenticationConfig {

    /**
     * 用于身份验证的 Spring 安全性筛选器链。
     *
     * @param http {@link HttpSecurity}
     * @return {@link SecurityFilterChain}
     */
    @Bean
    @Order(2)
    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/assets/**", "/webjars/**", "/login", "/test01/**", "/getCaptcha", "/getSmsCaptcha")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                // 在UsernamePasswordAuthenticationFilter拦截器之前添加验证码校验拦截器，并拦截POST的登录接口
                .addFilterBefore(new CaptchaAuthenticationFilter("/login"), UsernamePasswordAuthenticationFilter.class)
                .formLogin(formLogin -> formLogin.loginPage("/login"));
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.headers(AbstractHttpConfigurer::disable);
        // 添加BearerTokenAuthenticationFilter，将认证服务当做一个资源服务，解析请求头中的token
        return http.build();
    }


}
