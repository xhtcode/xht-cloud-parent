package com.xht.cloud.system.module.dept.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import lombok.Data;

/**
 * 描述 ：角色和部门关联
 *
 * @author : xht
 **/
@Data
@TableName(value = "sys_role_dept")
public class SysRoleDeptDO extends AbstractDO {

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private String deptId;

}
