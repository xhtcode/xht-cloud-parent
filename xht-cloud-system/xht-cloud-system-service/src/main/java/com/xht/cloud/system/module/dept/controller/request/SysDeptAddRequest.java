package com.xht.cloud.system.module.dept.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：部门-增加请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDeptRequest(部门-增加请求信息)", description = "部门-增加请求信息")
public class SysDeptAddRequest extends SysDeptRequest {

}
