package com.xht.cloud.system.module.log.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：系统操作日志记录-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysLogRequest(系统操作日志记录-修改请求信息)", description = "系统操作日志记录-修改请求信息")
public class SysLogUpdateRequest extends SysLogRequest {

    /**
     * 日志主键
     */
    @Schema(description = "日志主键")
    @NotBlank(message = "日志主键 `id` 校验不通过", groups = {Create.class, Update.class})
    private String id;

}
