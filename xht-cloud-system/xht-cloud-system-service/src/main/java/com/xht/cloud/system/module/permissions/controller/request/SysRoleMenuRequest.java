package com.xht.cloud.system.module.permissions.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：系统角色菜单关联表-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysRoleMenuRequest(系统角色菜单关联表-公共请求信息)", description = "系统角色菜单关联表-公共请求信息")
public class SysRoleMenuRequest extends Request {

    /**
     * 
     */
    @Schema(description = "")
    @NotBlank(message = " `roleId` 校验不通过", groups = {Create.class, Update.class})
    private String roleId;

    /**
     * 
     */
    @Schema(description = "")
    @NotBlank(message = " `menuId` 校验不通过", groups = {Create.class, Update.class})
    private String menuId;

}
