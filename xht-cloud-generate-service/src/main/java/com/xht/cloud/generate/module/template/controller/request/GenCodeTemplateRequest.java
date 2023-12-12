package com.xht.cloud.generate.module.template.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 描述 ：代码生成器-代码模板-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeTemplateRequest(代码生成器-代码模板-公共请求信息)", description = "代码生成器-代码模板-公共请求信息")
public class GenCodeTemplateRequest extends Request {

    /**
     * 分组id
     */
    @Schema(description = "分组id")
    @NotEmpty(message = "分组id `id` 校验不通过", groups = {Create.class, Update.class})
    private String groupId;

    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    @NotEmpty(message = "模板名称 `telName` 校验不通过", groups = {Create.class, Update.class})
    private String telName;

    /**
     * 模板描述
     */
    @Schema(description = "模板描述")
    private String telRemark;

    /**
     * 模板内容
     */
    @Schema(description = "模板内容")
    @NotEmpty(message = "模板内容 `telContent` 校验不通过", groups = {Create.class, Update.class})
    private String telContent;

    /**
     * 模板生成文件类型
     */
    @Schema(description = "模板生成文件类型")
    @NotEmpty(message = "文件类型 `telFileType` 校验不通过", groups = {Create.class, Update.class})
    private String telFileType;


    /**
     * 模板生成名称
     */
    @Schema(description = "模板生成名称")
    private String fileName;

    /**
     * 状态(0禁用1启用)
     */
    @Schema(description = "状态(0禁用1启用)")
    @NotNull(message = "状态 `telStatus` 校验不通过", groups = {Create.class, Update.class})
    private Integer telStatus;
    /**
     * 忽略字段
     */
    @Schema(description = "忽略字段")
    private String ignoreField;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private String telSort;

}
