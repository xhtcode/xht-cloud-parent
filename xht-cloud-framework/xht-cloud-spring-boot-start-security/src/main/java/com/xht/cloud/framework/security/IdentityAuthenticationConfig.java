package com.xht.cloud.framework.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 * @see <a target="_blank" href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html#defining-required-components">参考文档</a>
 **/
@SuppressWarnings("all")
public abstract class IdentityAuthenticationConfig {

    /**
     * 用于身份验证的 Spring 安全性筛选器链。
     *
     * @param http {@link HttpSecurity}
     * @return {@link SecurityFilterChain}
     */
    protected abstract SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception;
}
