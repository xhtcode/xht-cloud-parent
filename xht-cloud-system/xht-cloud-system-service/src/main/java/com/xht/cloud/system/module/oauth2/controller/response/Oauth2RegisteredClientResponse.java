package com.xht.cloud.system.module.oauth2.controller.response;

import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述 ：oauth2 客户端信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "Oauth2RegisteredClientResponse(oauth2 客户端信息-响应信息)", description = "oauth2 客户端信息")
public class Oauth2RegisteredClientResponse extends Response {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 客户端标识符
     */
    @Schema(description = "客户端标识符")
    private String clientId;

    /**
     * 客户端签发日期
     */
    @Schema(description = "客户端签发日期")
    private LocalDateTime clientIdIssuedAt;

    /**
     * 客户端密钥
     */
    @Schema(description = "客户端密钥")
    private String clientSecret;

    /**
     * 客户端过期时间
     */
    @Schema(description = "客户端过期时间")
    private LocalDateTime clientSecretExpiresAt;

    /**
     * 是否自动放行
     */
    @Schema(description = "是否自动放行")
    private String autoApprove;

    /**
     * 客户端名称
     */
    @Schema(description = "客户端名称")
    private String clientName;

    /**
     * 客户端可以使用的身份验证方法。支持的值为private_key_jwt,,and(publicclients).client_secret_basicclient_secret_postclient_secret_jwtnone
     */
    @Schema(description = "客户端可以使用的身份验证方法。支持的值为private_key_jwt,,and(publicclients).client_secret_basicclient_secret_postclient_secret_jwtnone")
    private String clientAuthenticationMethods;

    /**
     * 客户端可以使用的授权类型
     */
    @Schema(description = "客户端可以使用的授权类型")
    private String authorizationGrantTypes;

    /**
     * 重定向地址
     */
    @Schema(description = "重定向地址")
    private String redirectUris;

    /**
     * 客户端可用于注销的注销后重定向URI。
     */
    @Schema(description = "客户端可用于注销的注销后重定向URI。")
    private String postLogoutRedirectUris;

    /**
     * 允许客户端请求的范围
     */
    @Schema(description = "允许客户端请求的范围")
    private String scopes;

    /**
     * token有效期
     */
    @Schema(description = "token有效期")
    private Integer accessTokenValidity;

    /**
     * 刷新令牌有效期
     */
    @Schema(description = "刷新令牌有效期")
    private Integer refreshTokenValidity;

}
