package com.xht.cloud.system.module.user.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：用户表-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysUserRequest(用户表-公共请求信息)", description = "用户表-公共请求信息")
public class SysUserRequest extends Request {

    /**
     * 用户名(昵称)
     */
    @Schema(description = "用户名(昵称)")
    @NotBlank(message = "用户名(昵称) `nickName` 校验不通过", groups = {Create.class, Update.class})
    private String nickName;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    @NotBlank(message = "部门id校验不通过", groups = {Create.class, Update.class})
    private String deptId;

}
