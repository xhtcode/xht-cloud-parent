package com.xht.cloud.system.module.user.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述 ：用户表
 *
 * @author : xht
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "SysUserResponse(用户表-响应信息)", description = "用户表")
public class SysUserResponse extends Response {

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

    private String passWord;

    private String passWordSalt;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private String deptId;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String userAvatar;

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
     * 是否锁定 0-锁定 1-正常
     */
    @Schema(description = "是否锁定 0-锁定 1-正常")
    private String isLock;

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

}
