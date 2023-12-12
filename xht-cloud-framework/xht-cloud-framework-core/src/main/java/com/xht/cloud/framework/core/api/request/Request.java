package com.xht.cloud.framework.core.api.request;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：公共请求实体
 *
 * @author : 小糊涂
 **/
@Data
@Hidden
public abstract class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Hidden
    private Long t;

    /**
     * 当前页
     */
    @Schema(description = "当前页参数",defaultValue = "0")
    private long current;

    /**
     * 每页显示条数
     */
    @Schema(description = "当前页参数",defaultValue = "10")
    private long size;

    /**
     * 正序排序的字段名
     */
    @Schema(description = "正序排序的字段名",defaultValue = "字段1，字段2")
    private String ascName;

    /**
     * 倒序排序字段名
     */
    @Schema(description = "倒序排序字段名",defaultValue = "字段1，字段2")
    private String descName;

}
