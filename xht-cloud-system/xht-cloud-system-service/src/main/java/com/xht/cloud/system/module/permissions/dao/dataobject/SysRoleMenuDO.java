package com.xht.cloud.system.module.permissions.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import lombok.Data;

/**
 * 描述 ：系统角色菜单关联表
 *
 * @author : xht
 **/
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenuDO extends AbstractDO {

    /**
     *
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     *
     */
    @TableField(value = "menu_id")
    private String menuId;

}
