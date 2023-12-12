package com.xht.cloud.system.module.permissions.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：系统角色菜单关联表-增加请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysRoleMenuRequest(系统角色菜单关联表-增加请求信息)", description = "系统角色菜单关联表-增加请求信息")
public class SysRoleMenuAddRequest extends SysRoleMenuRequest {

}
