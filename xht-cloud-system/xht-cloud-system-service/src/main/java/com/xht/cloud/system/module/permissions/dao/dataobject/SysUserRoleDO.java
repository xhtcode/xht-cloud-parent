package com.xht.cloud.system.module.permissions.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import lombok.Data;

/**
 * 描述 ：用户角色
 *
 * @author : xht
 **/
@Data
@TableName(value = "sys_user_role")
public class SysUserRoleDO extends AbstractDO {

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private String roleId;

}
