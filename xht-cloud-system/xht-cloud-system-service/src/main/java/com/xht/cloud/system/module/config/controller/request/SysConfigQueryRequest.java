package com.xht.cloud.system.module.config.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：系统配置信息-查询请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysConfigRequest(系统配置信息-查询请求信息)", description = "系统配置信息-查询请求信息")
public class SysConfigQueryRequest extends SysConfigRequest {

}
