package com.xht.cloud.generate.module.template.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：-查询请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "GenCodeGroupRequest(-查询请求信息)", description = "-查询请求信息")
public class GenCodeGroupQueryRequest extends GenCodeGroupRequest {

}
