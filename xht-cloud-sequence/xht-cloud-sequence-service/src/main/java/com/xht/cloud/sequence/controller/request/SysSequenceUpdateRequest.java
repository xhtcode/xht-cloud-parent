package com.xht.cloud.sequence.controller.request;

import com.xht.cloud.framework.core.api.request.UpdateRequest;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.sequence.constant.SeqLoopEnum;
import com.xht.cloud.sequence.constant.SeqResetFlagEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 描述 ：序列生成器
 *
 * @author : 小糊涂
 **/
@Data
@Schema(name = "SysSequenceUpdateRequest(序列生成器-公共修改信息)", description = "序列生成器-公共修改信息")
public class SysSequenceUpdateRequest extends UpdateRequest<String> {

    /**
     * id
     */
    @Schema(description = "id")
    @NotEmpty(message = "id", groups = {Update.class})
    private String id;

    /**
     * 序列编码
     */
    @Schema(description = "序列编码")
    @NotEmpty(message = "序列编码不合法", groups = {Update.class})
    private String seqCode;

    /**
     * 序列名称
     */
    @Schema(description = "序列名称")
    @NotEmpty(message = "序列名称不合法", groups = {Update.class})
    private String seqName;

    /**
     * 序列当前值
     */
    @Schema(description = "序列当前值")
    @NotNull(message = "序列当前值不合法", groups = {Update.class})
    private Integer currentValue;

    /**
     * 序列步进值
     */
    @Schema(description = "序列步进值")
    @NotEmpty(message = "序列步进值不合法", groups = {Update.class})
    private Integer stepValue;

    /**
     * 序列最大值
     */
    @Schema(description = "序列最大值")
    @NotEmpty(message = "序列最大值不合法", groups = {Update.class})
    private Integer maxValue;

    /**
     * 序列最小值
     */
    @Schema(description = "序列最小值")
    @NotEmpty(message = "序列最小值不合法", groups = {Update.class})
    private Integer minValue;

    /**
     * 序列是否循环
     */
    @Schema(description = "序列是否循环")
    @NotEmpty(message = "序列是否循环不合法", groups = {Update.class})
    private SeqLoopEnum seqLoop;

    /**
     * 序列重置周期 0 不重置 0每天 1月 2年
     */
    @Schema(description = "序列重置周期")
    @NotEmpty(message = "序列重置周期不合法", groups = {Update.class})
    private SeqResetFlagEnum resetFlag;

    /**
     * 序列格式
     */
    @Schema(description = "序列格式")
    @NotEmpty(message = "序列格式不合法", groups = {Update.class})
    private String seqFormat;

    /**
     * 序列描述
     */
    @Schema(description = "序列描述")
    @NotEmpty(message = "序列描述不合法", groups = {Update.class})
    private String seqDesc;

    @Override
    public String getPkId() {
        return this.id;
    }

}
