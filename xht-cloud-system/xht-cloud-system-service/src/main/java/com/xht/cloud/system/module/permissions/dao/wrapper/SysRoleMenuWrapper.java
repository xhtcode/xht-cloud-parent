package com.xht.cloud.system.module.permissions.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleMenuDO;

import java.util.Objects;

/**
 * 描述 ：系统角色菜单关联表
 *
 * @author : xht
 **/
public final class SysRoleMenuWrapper implements EntityWrapper<SysRoleMenuDO> {

    /**
     * 私有化构造器
     */
    private SysRoleMenuWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysRoleMenuWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<SysRoleMenuDO> lambdaQuery(SysRoleMenuDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysRoleMenuDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getRoleId()), SysRoleMenuDO::getRoleId, entity.getRoleId())
                .eq(StringUtils.hasText(entity.getMenuId()), SysRoleMenuDO::getMenuId, entity.getMenuId())
                ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysRoleMenuDO> lambdaUpdate(SysRoleMenuDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysRoleMenuDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysRoleMenuDO::getRoleId, entity.getRoleId())
                .set(SysRoleMenuDO::getMenuId, entity.getMenuId())
                ;
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysRoleMenuWrapper wrapper;

        Instance() {
            wrapper = new SysRoleMenuWrapper();
        }

        public SysRoleMenuWrapper getInstance() {
            return wrapper;
        }
    }


}
