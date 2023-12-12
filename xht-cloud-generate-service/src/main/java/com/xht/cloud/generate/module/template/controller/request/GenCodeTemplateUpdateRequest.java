package com.xht.cloud.generate.module.template.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：代码生成器-代码模板-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeTemplateRequest(代码生成器-代码模板-修改请求信息)", description = "代码生成器-代码模板-修改请求信息")
public class GenCodeTemplateUpdateRequest extends GenCodeTemplateRequest {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id `id` 校验不通过", groups = {Update.class})
    private String id;

}
