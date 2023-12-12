package com.xht.cloud.generate.module.template.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@Schema(name = "GenCodeGroupTemplateRequest", description = "修改请求信息")
public class GenCodeGroupTemplateRequest extends Request {


    /**
     * 组id
     */
    @Schema(description = "组id")
    @NotBlank(message = "组id `groupId` 校验不通过", groups = {Create.class, Update.class})
    private String groupId;

    /**
     * 模板id
     */
    @Schema(description = "模板id")
    @NotBlank(message = "模板id `templateId` 校验不通过", groups = {Create.class, Update.class})
    private String templateId;
}
