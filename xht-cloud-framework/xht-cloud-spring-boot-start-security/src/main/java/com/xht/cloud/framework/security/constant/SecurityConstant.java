package com.xht.cloud.framework.security.constant;

import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

/**
 * 描述 ：security 常量
 *
 * @author : 小糊涂
 **/
public interface SecurityConstant {

    /**
     * 未知用户名称，或者是未登录的用户名称
     */
    String UNKNOWN_USER_NAME = "unknown";

    /**
     * oauth2 Grant Type类型 密码模式
     */
    String PASSWORD_GRANT_TYPE = "password";

    /**
     * {@code error_description} - 在授权响应和访问令牌响应中使用。
     *
     * @see OAuth2ParameterNames
     */
    String ERROR = "error";

    /**
     * {@code error_description} - 在授权响应和访问令牌响应中使用。
     *
     * @see OAuth2ParameterNames
     */
    String ERROR_CODE = "error_code";

    /**
     * {@code error_description} - 在授权响应和访问令牌响应中使用。
     *
     * @see OAuth2ParameterNames
     */
    String ERROR_DESCRIPTION = "error_description";

    /**
     * {@code error_uri} - 在授权响应和访问令牌响应中使用
     *
     * @see OAuth2ParameterNames
     */
    String ERROR_URI = "error_uri";
}
