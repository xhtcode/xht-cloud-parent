package com.xht.cloud.system.module.permissions.controller.response;

import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：系统角色菜单关联
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysRoleMenuResponse(系统角色菜单关联-响应信息)", description = "系统角色菜单关联")
public class SysRoleMenuResponse extends Response {

    /**
     *
     */
    @Schema(description = "")
    private String roleId;

    /**
     *
     */
    @Schema(description = "")
    private String menuId;

}
