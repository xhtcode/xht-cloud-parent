package com.xht.cloud.system.module.dict.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Query;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：字典数据表-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictItemRequest(字典数据表-公共请求信息)", description = "字典数据表-公共请求信息")
public class SysDictItemRequest extends Request {

    /**
     * 字典id
     */
    @Schema(description = "字典id")
    @NotBlank(message = "字典id `dictId` 不能为空", groups = {Query.class, Create.class, Update.class})
    private String dictId;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    @NotBlank(message = "字典编码 `dictCode` 不能为空", groups = {Create.class, Update.class})
    private String dictCode;

    /**
     * 字典值
     */
    @Schema(description = "字典值")
    @NotBlank(message = "字典值 `dictValue` 不能为空", groups = {Create.class, Update.class})
    private String dictValue;

    /**
     * 字典排序
     */
    @Schema(description = "字典排序")
    private Integer sortOrder;

    /**
     * 状态(0未启用1已经启用)
     */
    @Schema(description = "状态(0未启用1已经启用)")
    private String status;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

}
