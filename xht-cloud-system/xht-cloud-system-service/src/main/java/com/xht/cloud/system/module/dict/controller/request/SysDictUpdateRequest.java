package com.xht.cloud.system.module.dict.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：字典-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictRequest(字典-修改请求信息)", description = "字典-修改请求信息")
public class SysDictUpdateRequest extends SysDictRequest {

    /**
     * Id
     */
    @Schema(description = "Id")
    @NotBlank(message = "Id `id` 不能为空", groups = {Create.class, Update.class})
    private String id;

}
