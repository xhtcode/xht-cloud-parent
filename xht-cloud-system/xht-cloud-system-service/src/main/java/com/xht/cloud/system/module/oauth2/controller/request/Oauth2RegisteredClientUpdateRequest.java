package com.xht.cloud.system.module.oauth2.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：oauth2 客户端信息-修改请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "Oauth2RegisteredClientRequest(oauth2 客户端信息-修改请求信息)", description = "oauth2 客户端信息-修改请求信息")
public class Oauth2RegisteredClientUpdateRequest extends Oauth2RegisteredClientRequest {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id `id` 校验不通过", groups = {Create.class, Update.class})
    private String id;

}
