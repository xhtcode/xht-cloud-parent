package com.xht.cloud.system.module.poetry.controller.response;

import com.xht.cloud.framework.core.api.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictPoetryResponse(-响应信息)", description = "")
public class SysDictPoetryResponse extends Response {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

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
