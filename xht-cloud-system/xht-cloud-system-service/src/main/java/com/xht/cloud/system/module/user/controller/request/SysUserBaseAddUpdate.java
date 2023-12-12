package com.xht.cloud.system.module.user.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@Schema(name = "SysUserRequest(用户表-增改)", description = "用户表-增改")
public class SysUserBaseAddUpdate {

    @Valid
    @NotNull(message = "用户信息不通过", groups = {Create.class, Update.class})
    private SysUserUpdateRequest sysUser;

    @Valid
    @NotNull(message = "用户基础校验不通过", groups = {Create.class, Update.class})
    private SysUserProfileRequest profile;

}
