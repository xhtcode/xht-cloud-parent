package com.xht.cloud.system.module.user.controller.request;

import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：用户表-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysUserRequest(用户表-修改请求信息)", description = "用户表-修改请求信息")
public class SysUserUpdateRequest extends SysUserRequest {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotBlank(message = "用户ID `id` 校验不通过", groups = {Update.class})
    private String id;

}
