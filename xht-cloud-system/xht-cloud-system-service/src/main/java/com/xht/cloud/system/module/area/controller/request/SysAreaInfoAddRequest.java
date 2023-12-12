package com.xht.cloud.system.module.area.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：地区信息-增加请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysAreaInfoRequest(地区信息-增加请求信息)", description = "地区信息-增加请求信息")
public class SysAreaInfoAddRequest extends SysAreaInfoRequest {

}
