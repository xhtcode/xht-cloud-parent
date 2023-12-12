package com.xht.cloud.generate.module.type.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：代码生成器-字段类型对应-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenColumnTypeRequest(代码生成器-字段类型对应-公共请求信息)", description = "代码生成器-字段类型对应-公共请求信息")
public class GenColumnTypeRequest extends Request {

    /**
     * 数据库类型
     */
    @Schema(description = "数据库类型")
    @NotBlank(message = "数据库类型 `dbLabel` 校验不通过", groups = {Create.class, Update.class})
    private String dbLabel;

    /**
     * 数据库字段类型
     */
    @Schema(description = "数据库字段类型")
    private String dbValue;

    /**
     * 语言类型
     */
    @Schema(description = "语言类型")
    private String label;

    /**
     * 代码类型
     */
    @Schema(description = "代码类型")
    private String value;

}
