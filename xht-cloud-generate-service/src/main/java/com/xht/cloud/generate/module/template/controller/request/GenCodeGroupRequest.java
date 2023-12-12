package com.xht.cloud.generate.module.template.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeGroupRequest(-公共请求信息)", description = "-公共请求信息")
public class GenCodeGroupRequest extends Request {

    /**
     * 组名称
     */
    @Schema(description = "组名称")
    private String groupName;

    /**
     * 组描述
     */
    @Schema(description = "组描述")
    private String groupDesc;

}
