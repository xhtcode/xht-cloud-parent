package com.xht.cloud.system.module.oauth2.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.IntegerInterval;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 描述 ：oauth2 客户端信息-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "Oauth2RegisteredClientRequest(oauth2 客户端信息-公共请求信息)", description = "oauth2 客户端信息-公共请求信息")
public class Oauth2RegisteredClientRequest extends Request {

    /**
     * 客户端标识符
     */
    @Schema(description = "客户端标识符")
    @NotBlank(message = "客户端标识符 `clientId` 校验不通过", groups = {Create.class, Update.class})
    private String clientId;

    /**
     * 客户端签发日期
     */
    @Schema(description = "客户端签发日期")
    @NotNull(message = "客户端签发日期 `clientIdIssuedAt` 校验不通过", groups = {Create.class, Update.class})
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
    @NotBlank(message = "是否自动放行 `autoApprove` 校验不通过", groups = {Create.class, Update.class})
    private String autoApprove;

    /**
     * 客户端名称
     */
    @Schema(description = "客户端名称")
    @NotBlank(message = "客户端名称 `clientName` 校验不通过", groups = {Create.class, Update.class})
    private String clientName;

    /**
     * 客户端可以使用的身份验证方法。支持的值为private_key_jwt,,and(publicclients).client_secret_basicclient_secret_postclient_secret_jwtnone
     */
    @Schema(description = "客户端可以使用的身份验证方法。支持的值为private_key_jwt,,and(publicclients).client_secret_basicclient_secret_postclient_secret_jwtnone")
    @NotBlank(message = "客户端可以使用的身份验证方法。支持的值为private_key_jwt,,and(publicclients).client_secret_basicclient_secret_postclient_secret_jwtnone `clientAuthenticationMethods` 校验不通过", groups = {Create.class, Update.class})
    private String clientAuthenticationMethods;

    /**
     * 客户端可以使用的授权类型
     */
    @Schema(description = "客户端可以使用的授权类型")
    @NotBlank(message = "客户端可以使用的授权类型 `authorizationGrantTypes` 校验不通过", groups = {Create.class, Update.class})
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
    @NotBlank(message = "允许客户端请求的范围 `scopes` 校验不通过", groups = {Create.class, Update.class})
    private String scopes;

    /**
     * token有效期
     */
    @Schema(description = "token有效期")
    @IntegerInterval(message = "token有效期 `accessTokenValidity` 字段值在0到999之间", groups = {Create.class, Update.class})
    private Integer accessTokenValidity;

    /**
     * 刷新令牌有效期
     */
    @Schema(description = "刷新令牌有效期")
    private Integer refreshTokenValidity;

}
