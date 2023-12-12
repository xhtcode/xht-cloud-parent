package com.xht.cloud.system.module.dict.controller.response;

import lombok.Data;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class SysDictVo extends SysDictResponse {

    private List<SysDictItemResponse> children;

}
