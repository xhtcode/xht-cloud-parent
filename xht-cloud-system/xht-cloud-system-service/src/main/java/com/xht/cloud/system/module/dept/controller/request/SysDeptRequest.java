package com.xht.cloud.system.module.dept.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 描述 ：部门-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysDeptRequest(部门-公共请求信息)", description = "部门-公共请求信息")
public class SysDeptRequest extends Request {

    /**
     * 父id
     */
    @Schema(description = "父id")
    @NotBlank(message = "父id `parentId` 校验不通过", groups = {Create.class, Update.class})
    private String parentId;

    /**
     * 指定主管ID(用户id)
     */
    @Schema(description = "指定主管ID(用户id)")
    private String directorId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @NotBlank(message = "部门名称 `deptName` 校验不通过", groups = {Create.class, Update.class})
    private String deptName;

    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    @NotBlank(message = "部门编码 `deptCode` 校验不通过", groups = {Create.class, Update.class})
    private String deptCode;

    /**
     * 部门分类(0-境内 1境外)
     */
    @Schema(description = "部门分类(0-境内 1境外)")
    @NotBlank(message = "部门分类(0-境内 1境外) `deptType` 校验不通过", groups = {Create.class, Update.class})
    private String deptType;

    /**
     * 部门负责人
     */
    @Schema(description = "部门负责人")
    private String deptLeader;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String deptTel;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer deptSort;

    /**
     * 部门状态（0停用1正常）
     */
    @Schema(description = "部门状态（0停用1正常）")
    @NotBlank(message = "部门状态（0停用1正常） `deptStatus` 校验不通过", groups = {Create.class, Update.class})
    private String deptStatus;

    /**
     * 部门描述
     */
    @Schema(description = "部门描述")
    private String description;

}
