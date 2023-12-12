package com.xht.cloud.system.module.config.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.IntegerInterval;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：系统配置信息-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysConfigRequest(系统配置信息-公共请求信息)", description = "系统配置信息-公共请求信息")
public class SysConfigRequest extends Request {

    /**
     * 配置编码
     */
    @Schema(description = "配置编码")
    @NotBlank(message = "配置编码 `configCode` 校验不通过", groups = {Create.class, Update.class})
    private String configCode;

    /**
     * 配置名称
     */
    @Schema(description = "配置名称")
    @NotBlank(message = "配置名称 `configName` 校验不通过", groups = {Create.class, Update.class})
    private String configName;

    /**
     * 配置信息(存放json)
     */
    @Schema(description = "配置信息(存放json)")
    @NotBlank(message = "配置信息(存放json) `configInfo` 校验不通过", groups = {Create.class, Update.class})
    private String configInfo;

    /**
     * 转换的类名称(默认转换成map)
     */
    @Schema(description = "转换的类名称(默认转换成map)")
    private String className;

    /**
     * 配置描述
     */
    @Schema(description = "配置描述")
    private String description;

    /**
     * 状态（1正常0停用）
     */
    @Schema(description = "状态（1正常0停用）")
    @IntegerInterval(message = "状态（1正常0停用） `status` 字段值在0到999之间", groups = {Create.class, Update.class})
    private Integer status;

}
