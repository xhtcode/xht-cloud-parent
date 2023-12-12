package com.xht.cloud.system.module.poetry.controller.request;

import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：-增加请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDictPoetryRequest(-增加请求信息)", description = "-增加请求信息")
public class SysDictPoetryAddRequest extends SysDictPoetryRequest {

}
