package com.xht.cloud.system.module.dict.controller.response;

import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：字典数据
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictItemResponse(字典数据-响应信息)", description = "字典数据")
public class SysDictItemResponse extends Response {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 字典id
     */
    @Schema(description = "字典id")
    private String dictId;

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
