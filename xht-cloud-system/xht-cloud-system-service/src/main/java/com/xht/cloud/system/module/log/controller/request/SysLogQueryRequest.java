package com.xht.cloud.system.module.log.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：系统操作日志记录-查询请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysLogRequest(系统操作日志记录-查询请求信息)", description = "系统操作日志记录-查询请求信息")
public class SysLogQueryRequest extends SysLogRequest {

}
