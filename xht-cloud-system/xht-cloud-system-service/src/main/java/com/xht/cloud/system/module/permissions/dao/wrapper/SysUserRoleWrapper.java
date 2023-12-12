package com.xht.cloud.system.module.permissions.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysUserRoleDO;
import java.util.Objects;
import org.springframework.util.ObjectUtils;

/**
 * 描述 ：用户角色
 *
 * @author : xht
 **/
public final class SysUserRoleWrapper implements EntityWrapper<SysUserRoleDO> {

    /**
     * 私有化构造器
     */
    private SysUserRoleWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysUserRoleWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysUserRoleWrapper wrapper;

        Instance() {
            wrapper = new SysUserRoleWrapper();
        }

        public SysUserRoleWrapper getInstance() {
            return wrapper;
        }
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<SysUserRoleDO> lambdaQuery(SysUserRoleDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysUserRoleDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(!ObjectUtils.isEmpty(entity.getUserId()), SysUserRoleDO::getUserId, entity.getUserId())
                .eq(!ObjectUtils.isEmpty(entity.getRoleId()), SysUserRoleDO::getRoleId, entity.getRoleId())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysUserRoleDO> lambdaUpdate(SysUserRoleDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysUserRoleDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
        ;
    }


}
