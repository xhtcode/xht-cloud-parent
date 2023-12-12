package com.xht.cloud.system.module.permissions.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：菜单权限表-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysMenuRequest(菜单权限表-修改请求信息)", description = "菜单权限表-修改请求信息")
public class SysMenuUpdateRequest extends SysMenuRequest {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id `id` 不能为空", groups = {Create.class, Update.class})
    private String id;

}
