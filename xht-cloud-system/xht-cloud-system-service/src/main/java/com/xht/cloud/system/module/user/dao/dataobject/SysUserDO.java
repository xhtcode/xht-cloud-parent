package com.xht.cloud.system.module.user.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述 ：用户表
 *
 * @author : xht
 **/
@Data
@TableName(value = "sys_user")
public class SysUserDO extends BaseDO {

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户名(昵称)
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户名(账号)
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "pass_word")
    private String passWord;

    /**
     * 密码盐值
     */
    @TableField(value = "pass_word_salt")
    private String passWordSalt;

    /**
     * 旧密码
     */
    @TableField(value = "pass_word_old")
    private String passWordOld;

    /**
     * 旧密码盐值
     */
    @TableField(value = "pass_word_salt_old")
    private String passWordSaltOld;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private String deptId;

    /**
     * 头像地址
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * 账号类型，0 系统管理员 1 用户 2 商家
     */
    @TableField(value = "user_type")
    private String userType;

    /**
     * QQ互联openid
     */
    @TableField(value = "qq_openid")
    private String qqOpenid;

    /**
     * 微信openid
     */
    @TableField(value = "wx_openid")
    private String wxOpenid;

    /**
     * 微信unionid
     */
    @TableField(value = "wx_unionid")
    private String wxUnionid;

    /**
     * 是否锁定 0-锁定 1-正常
     */
    @TableField(value = "is_lock")
    private String isLock;

    /**
     * 是否激活，0-未激活，1-激活
     */
    @TableField(value = "is_active")
    private String isActive;

    /**
     * 是否超级管理员，0-不是，1-是
     */
    @TableField(value = "is_admin")
    private String isAdmin;

    /**
     * 注册时间
     */
    @TableField(value = "registered_time")
    private LocalDateTime registeredTime;

    /**
     * 最后一次登录时间
     */
    @TableField(value = "last_login_time")
    private LocalDateTime lastLoginTime;

}
