package com.xht.cloud.system.module.user.controller.response;

import com.xht.cloud.system.module.dept.controller.response.SysDeptResponse;
import lombok.Data;

import java.util.Set;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class SysUserVo {

    private SysUserResponse sysUser;

    private SysUserProfileResponse profile;

    /**
     * 角色编码
     */
    private Set<String> roleCode;

    /**
     * 数据权限 {@link com.xht.cloud.framework.mybatis.core.enums.DeptUserDataScopeEnum}
     */
    private Integer dataScope;

    /**
     * 菜单权限或者其他权限
     */
    private Set<String> authorities;

    /**
     * 部门信息
     */
    private SysDeptResponse dept;
}
