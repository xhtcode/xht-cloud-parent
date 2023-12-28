package com.xht.cloud.sequence.controller.request;

import com.xht.cloud.framework.core.api.request.PageQueryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述 ：序列生成器
 *
 * @author : 小糊涂
 **/
@Data
@Schema(name = "SysSequenceQueryRequest(序列生成器-公共请求信息)", description = "序列生成器-公共请求信息")
public class SysSequenceQueryRequest extends PageQueryRequest {

    /**
     * 序列编码
     */
    @Schema(description = "序列编码")
    private String seqCode;

    /**
     * 序列名称
     */
    @Schema(description = "序列名称")
    private String seqName;

}
