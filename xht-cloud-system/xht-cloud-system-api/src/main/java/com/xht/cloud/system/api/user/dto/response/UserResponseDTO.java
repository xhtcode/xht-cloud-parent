package com.xht.cloud.system.api.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 描述 ：用户表
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysUserResponse(用户表-响应信息)", description = "用户表")
public class UserResponseDTO {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String id;

    /**
     * 用户名(昵称)
     */
    @Schema(description = "用户名(昵称)")
    private String nickName;

    /**
     * 用户名(账号)
     */
    @Schema(description = "用户名(账号)")
    private String userName;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String passWord;

    @Schema(description = "密码盐值")
    private String passWordSalt;

    /**
     * 账号类型，0 系统管理员 1 用户 2 商家
     */
    @Schema(description = "账号类型，0 系统管理员 1 用户 2 商家")
    private String userType;

    /**
     * QQ互联openid
     */
    @Schema(description = "QQ互联openid")
    private String qqOpenid;

    /**
     * 微信openid
     */
    @Schema(description = "微信openid")
    private String wxOpenid;

    /**
     * 微信unionid
     */
    @Schema(description = "微信unionid")
    private String wxUnionid;

    /**
     * 是否激活，0-未激活，1-激活
     */
    @Schema(description = "是否激活，0-未激活，1-激活")
    private String isActive;

    /**
     * 是否超级管理员，0-不是，1-是
     */
    @Schema(description = "是否超级管理员，0-不是，1-是")
    private String isAdmin;

    /**
     * 注册时间
     */
    @Schema(description = "注册时间")
    private LocalDateTime registeredTime;

    /**
     * 最后一次登录时间
     */
    @Schema(description = "最后一次登录时间")
    private LocalDateTime lastLoginTime;

    private Set<String> roleCode;

    private Integer dataScope;

    private String deptId;
}
