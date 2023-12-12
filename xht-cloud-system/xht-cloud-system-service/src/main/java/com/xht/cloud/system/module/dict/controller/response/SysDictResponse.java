package com.xht.cloud.system.module.dict.controller.response;

import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：字典
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictResponse(字典-响应信息)", description = "字典")
public class SysDictResponse extends Response {

    /**
     * Id
     */
    @Schema(description = "Id")
    private String id;

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
