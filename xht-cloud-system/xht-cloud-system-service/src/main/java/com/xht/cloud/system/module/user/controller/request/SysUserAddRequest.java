package com.xht.cloud.system.module.user.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：用户表-增加请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysUserRequest(用户表-增加请求信息)", description = "用户表-增加请求信息")
public class SysUserAddRequest extends SysUserRequest {

}
