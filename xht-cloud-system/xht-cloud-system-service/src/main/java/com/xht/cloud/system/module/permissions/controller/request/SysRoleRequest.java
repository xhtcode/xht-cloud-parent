package com.xht.cloud.system.module.permissions.controller.request;

import com.xht.cloud.framework.core.api.request.Request;
import com.xht.cloud.framework.mybatis.core.enums.DeptUserDataScopeEnum;
import com.xht.cloud.framework.web.validation.IntegerInterval;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 描述 ：系统角色表-公共请求信息
 *
 * @author : xht
 **/
@Data
@Schema(name = "SysRoleRequest(系统角色表-公共请求信息)", description = "系统角色表-公共请求信息")
public class SysRoleRequest extends Request {

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @NotBlank(message = "角色名称 `roleName` 校验不通过", groups = {Create.class, Update.class})
    private String roleName;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    @NotBlank(message = "角色编码 `roleCode` 校验不通过", groups = {Create.class, Update.class})
    private String roleCode;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    @IntegerInterval(message = "显示顺序 `roleSort` 字段值在0到999之间", groups = {Create.class, Update.class})
    private Integer roleSort;

    /**
     * 数据权限
     * <li class="el-select-dropdown__item"><span>1全部数据权限</span></li>
     * <li class="el-select-dropdown__item selected"><span>2自定数据权限</span></li>
     * <li class="el-select-dropdown__item hover"><span>3本部门数据权限</span></li>
     * <li class="el-select-dropdown__item"><span>4本部门及以下数据权限</span></li>
     * <li class="el-select-dropdown__item"><span>5仅本人数据权限</span></li>
     */
    @Schema(description = "数据权限")
    @NotNull(message = "数据权限 `dataScope` 校验不通过", groups = {Create.class, Update.class})
    private DeptUserDataScopeEnum dataScope;

    /**
     * 角色状态（0停用1正常）
     */
    @Schema(description = "角色状态（0停用1正常）")
    @NotBlank(message = "角色状态 `status` 校验不通过", groups = {Create.class, Update.class})
    private String status;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String roleDesc;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private List<String> deptIds;

}
