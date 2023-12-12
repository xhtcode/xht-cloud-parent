package com.xht.cloud.generate.module.config.controller.response;

import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：代码生成器-配置中心
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeConfigResponse(代码生成器-配置中心-响应信息)", description = "代码生成器-配置中心")
public class GenCodeConfigResponse extends Response {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 配置名称
     */
    @Schema(description = "配置名称")
    private String configName;

    /**
     * 配置描述
     */
    @Schema(description = "配置描述")
    private String configDesc;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer configSort;


    /**
     * 配置详情
     */
    @Schema(description = "配置详情")
    private String configInfo;

}
