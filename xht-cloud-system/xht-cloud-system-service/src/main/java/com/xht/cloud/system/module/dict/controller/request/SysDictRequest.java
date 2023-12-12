package com.xht.cloud.system.module.dict.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：字典-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictRequest(字典-公共请求信息)", description = "字典-公共请求信息")
public class SysDictRequest extends Request {

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;

    /**
     * 字典值
     */
    @Schema(description = "字典值")
    private String dictValue;

    /**
     * 状态(0未启用1已经启用)
     */
    @Schema(description = "状态(0未启用1已经启用)")
    @NotBlank(message = "状态(0未启用1已经启用) `dictStatus` 不能为空", groups = {Create.class, Update.class})
    private String dictStatus;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String description;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortOrder;

}
