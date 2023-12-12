package com.xht.cloud.system.module.user.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 描述 ：用户表-查询请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysUserRequest(用户表-查询请求信息)", description = "用户表-查询请求信息")
public class SysUserQueryRequest extends SysUserRequest {

    /**
     * 用户名(账号)
     */
    @Schema(description = "用户名(账号)")
    private String userName;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private List<String> userIds;


    /**
     * 是否激活，0-未激活，1-激活
     */
    @Schema(description = "是否激活，0-未激活，1-激活")
    private String isActive;

    /**
     * 账号类型，0：自主注册，1：QQ，2：微信
     */
    @Schema(description = "账号类型，0：自主注册，1：QQ，2：微信")
    private String userType;
}
