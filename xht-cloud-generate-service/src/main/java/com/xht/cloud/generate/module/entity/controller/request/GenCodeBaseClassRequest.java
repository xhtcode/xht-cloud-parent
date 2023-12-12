package com.xht.cloud.generate.module.entity.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：代码生成器-基类-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeBaseClassRequest(代码生成器-基类-公共请求信息)", description = "代码生成器-基类-公共请求信息")
public class GenCodeBaseClassRequest extends Request {

    /**
     * 类名
     */
    @Schema(description = "类名")
    private String className;

    /**
     * 包名
     */
    @Schema(description = "包名")
    private String packageName;

    /**
     * 忽略字段(写数据库字段)
     */
    @Schema(description = "忽略字段(写数据库字段)")
    private String ignoreField;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private String classSort;

}
