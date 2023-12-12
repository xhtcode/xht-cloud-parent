package com.xht.cloud.generate.module.column.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：代码生成业务字段-查询请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenTableColumnRequest(代码生成业务字段-查询请求信息)", description = "代码生成业务字段-查询请求信息")
public class GenTableColumnQueryRequest extends GenTableColumnRequest {

}
