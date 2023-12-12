package com.xht.cloud.system.module.area.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class SysAreaInfoVo extends SysAreaInfoResponse {

    @Schema(description = "下级城市")
    private List<SysAreaInfoResponse> children;
}
