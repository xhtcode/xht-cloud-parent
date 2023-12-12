package com.xht.cloud.generate.module.config.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：代码生成器-配置中心-查询请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeConfigRequest(代码生成器-配置中心-查询请求信息)", description = "代码生成器-配置中心-查询请求信息")
public class GenCodeConfigQueryRequest extends GenCodeConfigRequest {

}
