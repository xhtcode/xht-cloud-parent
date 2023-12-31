package com.xht.cloud.generate.module.column.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.IntegerInterval;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：代码生成业务字段-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenTableColumnRequest(代码生成业务字段-公共请求信息)", description = "代码生成业务字段-公共请求信息")
public class GenTableColumnRequest extends Request {

    /**
     * 表id
     */
    @Schema(description = "表id")
    @NotBlank(message = "表id `tableId` 校验不通过", groups = {Create.class, Update.class})
    private String tableId;

    /**
     * 表所在的数据库名称
     */
    @Schema(description = "表所在的数据库名称")
    @NotBlank(message = "表所在的数据库名称 `tableSchema` 校验不通过", groups = {Create.class, Update.class})
    private String tableSchema;

    /**
     * 表名称
     */
    @Schema(description = "表名称")
    @NotBlank(message = "表名称 `tableName` 校验不通过", groups = {Create.class, Update.class})
    private String tableName;

    /**
     * 字段名字
     */
    @Schema(description = "字段名字")
    @NotBlank(message = "字段名字 `columnName` 校验不通过", groups = {Create.class, Update.class})
    private String columnName;

    /**
     * 字段长度
     */
    @Schema(description = "字段长度")
    @IntegerInterval(message = "字段长度 `columnLength` 字段值在0到999之间", groups = {Create.class, Update.class})
    private Integer columnLength;

    /**
     * 字段名字
     */
    @Schema(description = "字段名字")
    @NotBlank(message = "字段名字 `columnCodeName` 校验不通过", groups = {Create.class, Update.class})
    private String columnCodeName;

    /**
     * 字段描述
     */
    @Schema(description = "字段描述")
    @NotBlank(message = "字段描述 `columnComment` 校验不通过", groups = {Create.class, Update.class})
    private String columnComment;

    /**
     * 数据库字段类型
     */
    @Schema(description = "数据库字段类型")
    @NotBlank(message = "数据库字段类型 `columnDbType` 校验不通过", groups = {Create.class, Update.class})
    private String columnDbType;

    /**
     * Java字段类型
     */
    @Schema(description = "Java字段类型")
    @NotBlank(message = "Java字段类型 `columnJavaType` 校验不通过", groups = {Create.class, Update.class})
    private String columnJavaType;

    /**
     * Ts字段名称
     */
    @Schema(description = "Ts字段名称")
    @NotBlank(message = "Ts字段名称 `columnTsType` 校验不通过", groups = {Create.class, Update.class})
    private String columnTsType;

    /**
     * 字段值示例
     */
    @Schema(description = "字段值示例")
    private String columnExample;

    /**
     * 是否主键（1是）
     */
    @Schema(description = "是否主键（1是）")
    private String columnPk;

    /**
     * 是否列表（1是）
     */
    @Schema(description = "是否列表（1是）")
    private String columnList;

    /**
     * 是否增加（1是）
     */
    @Schema(description = "是否增加（1是）")
    private String columnSave;

    /**
     * 是否修改（1是）
     */
    @Schema(description = "是否修改（1是）")
    private String columnUpdate;

    /**
     * 是否查询（1是）
     */
    @Schema(description = "是否查询（1是）")
    private String columnQuery;

    /**
     * 是否必填（1是）
     */
    @Schema(description = "是否必填（1是）")
    private String columnNullable;

    /**
     * 是否字典（1是）
     */
    @Schema(description = "是否字典（1是）")
    private String columnDict;

    /**
     * 字段显示
     */
    @Schema(description = "字段显示")
    private String columnHtml;

    /**
     * 字段排序
     */
    @Schema(description = "字段排序")
    private Integer columnSort;

    /**
     * 查询方式
     */
    @Schema(description = "查询方式")
    private String columnQueryType;
}
