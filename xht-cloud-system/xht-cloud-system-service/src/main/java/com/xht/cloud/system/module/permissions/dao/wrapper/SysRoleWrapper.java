package com.xht.cloud.system.module.permissions.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：系统角色表
 *
 * @author : xht
 **/
public final class SysRoleWrapper implements EntityWrapper<SysRoleDO> {

    /**
     * 私有化构造器
     */
    private SysRoleWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysRoleWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysRoleWrapper wrapper;

        Instance() {
            wrapper = new SysRoleWrapper();
        }

        public SysRoleWrapper getInstance() {
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
    public LambdaQueryWrapper<SysRoleDO> lambdaQuery(SysRoleDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysRoleDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysRoleDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getRoleName()), SysRoleDO::getRoleName, entity.getRoleName())
                .eq(StringUtils.hasText(entity.getRoleCode()), SysRoleDO::getRoleCode, entity.getRoleCode())
                .eq(!ObjectUtils.isEmpty(entity.getRoleSort()), SysRoleDO::getRoleSort, entity.getRoleSort())
                .eq(StringUtils.hasText(entity.getStatus()), SysRoleDO::getStatus, entity.getStatus())
                .eq(StringUtils.hasText(entity.getRoleDesc()), SysRoleDO::getRoleDesc, entity.getRoleDesc())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysRoleDO> lambdaUpdate(SysRoleDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysRoleDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysRoleDO::getRoleName, entity.getRoleName())
                .set(SysRoleDO::getRoleCode, entity.getRoleCode())
                .set(SysRoleDO::getRoleSort, entity.getRoleSort())
                .set(SysRoleDO::getStatus, entity.getStatus())
                .set(SysRoleDO::getRoleDesc, entity.getRoleDesc())
        ;
    }


}
