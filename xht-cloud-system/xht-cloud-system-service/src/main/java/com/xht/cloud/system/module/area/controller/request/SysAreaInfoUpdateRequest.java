package com.xht.cloud.system.module.area.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：地区信息-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysAreaInfoRequest(地区信息-修改请求信息)", description = "地区信息-修改请求信息")
public class SysAreaInfoUpdateRequest extends SysAreaInfoRequest {

    /**
     * 主键
     */
    @Schema(description = "主键")
    @NotBlank(message = "主键 `id` 校验不通过", groups = {Create.class, Update.class})
    private String id;

}
