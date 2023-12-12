package com.xht.cloud.system.module.dept.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.IntegerInterval;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：岗位信息-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysPositionRequest(岗位信息-公共请求信息)", description = "岗位信息-公共请求信息")
public class SysPositionRequest extends Request {

    /**
     * 父id
     */
    @Schema(description = "父id")
    private String parentId;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private String deptId;

    /**
     * 岗位编码
     */
    @Schema(description = "岗位编码")
    @NotBlank(message = "岗位编码 `positionCode` 校验不通过", groups = {Create.class, Update.class})
    private String positionCode;

    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    @NotBlank(message = "岗位名称 `positionName` 校验不通过", groups = {Create.class, Update.class})
    private String positionName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @IntegerInterval(message = "排序 `sort` 字段值在0到999之间", groups = {Create.class, Update.class})
    private Integer sort;

    /**
     * 状态（1正常0停用）
     */
    @Schema(description = "状态（1正常0停用）")
    @IntegerInterval(message = "状态（1正常0停用） `status` 字段值在0到999之间", groups = {Create.class, Update.class})
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String description;

}
