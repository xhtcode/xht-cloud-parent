package com.xht.cloud.generate.module.column.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：代码生成业务字段-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenTableColumnRequest(代码生成业务字段-修改请求信息)", description = "代码生成业务字段-修改请求信息")
public class GenTableColumnUpdateRequest extends GenTableColumnRequest {

    /**
     * 字段名字
     */
    @Schema(description = "字段名字")
    @NotBlank(message = "字段名字 `id` 校验不通过", groups = {Create.class, Update.class})
    private String id;

}
