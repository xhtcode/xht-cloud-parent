package com.xht.cloud.system.module.poetry.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictPoetryRequest(-公共请求信息)", description = "-公共请求信息")
public class SysDictPoetryRequest extends Request {

    /**
     * 作者
     */
    @Schema(description = "作者")
    private String author;

    /**
     * 作者简介
     */
    @Schema(description = "作者简介")
    private String authorDesc;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;

    /**
     * 简介
     */
    @Schema(description = "简介")
    private String typeDesc;

    /**
     * 作品名字
     */
    @Schema(description = "作品名字")
    private String title;

    /**
     * 作品名字简介
     */
    @Schema(description = "作品名字简介")
    private String tilteDesc;

    /**
     * 作品内容
     */
    @Schema(description = "作品内容")
    private String context;

    /**
     * 作品内容简介
     */
    @Schema(description = "作品内容简介")
    private String contextDesc;

}
