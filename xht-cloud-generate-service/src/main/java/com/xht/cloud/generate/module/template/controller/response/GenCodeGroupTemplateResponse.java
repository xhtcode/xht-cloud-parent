package com.xht.cloud.generate.module.template.controller.response;

import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeGroupTemplateResponse(-响应信息)", description = "")
public class GenCodeGroupTemplateResponse extends Response {

    /**
     * 组id
     */
    @Schema(description = "组id")
    private String groupId;

    /**
     * 模板id
     */
    @Schema(description = "模板id")
    private String templateId;

}
