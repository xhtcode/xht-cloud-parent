package com.xht.cloud.framework.security.dto;

import lombok.Data;

import java.util.Set;

/**
 * 描述 ：用户表
 *
 * @author : xht
 **/
@Data
public class UserResponseDTO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名(昵称)
     */
    private String nickName;

    /**
     * 用户名(账号)
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    private String passWordSalt;

    /**
     * 账号类型，0 系统管理员 1 用户 2 商家
     */
    private String userType;

    /**
     * QQ互联openid
     */
    private String qqOpenid;

    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * 微信unionid
     */
    private String wxUnionid;

    /**
     * 是否锁定 0-锁定 1-正常
     */
    private String isLock;

    /**
     * 是否激活，0-未激活，1-激活
     */
    private String isActive;

    /**
     * 是否超级管理员，0-不是，1-是
     */
    private String isAdmin;

    /**
     * 注册时间
     */
    private String registeredTime;

    /**
     * 最后一次登录时间
     */
    private String lastLoginTime;

    private Set<String> roleCode;

    private Set<String> authorities;

    private Integer dataScope;

    private String deptId;
}
