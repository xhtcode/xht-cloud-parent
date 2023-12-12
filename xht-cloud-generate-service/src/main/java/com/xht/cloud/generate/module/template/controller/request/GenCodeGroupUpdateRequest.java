package com.xht.cloud.generate.module.template.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeGroupRequest(-修改请求信息)", description = "-修改请求信息")
public class GenCodeGroupUpdateRequest extends GenCodeGroupRequest {

    /**
     * 
     */
    @Schema(description = "")
    @NotBlank(message = " `id` 校验不通过", groups = {Create.class, Update.class})
    private String id;

}
