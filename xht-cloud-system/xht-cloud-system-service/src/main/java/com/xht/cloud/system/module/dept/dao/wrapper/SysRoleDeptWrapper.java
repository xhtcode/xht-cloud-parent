package com.xht.cloud.system.module.dept.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.dept.dao.dataobject.SysRoleDeptDO;
import java.util.Objects;
import org.springframework.util.ObjectUtils;

/**
 * 描述 ：角色和部门关联
 *
 * @author : xht
 **/
public final class SysRoleDeptWrapper implements EntityWrapper<SysRoleDeptDO> {

    /**
     * 私有化构造器
     */
    private SysRoleDeptWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysRoleDeptWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysRoleDeptWrapper wrapper;

        Instance() {
            wrapper = new SysRoleDeptWrapper();
        }

        public SysRoleDeptWrapper getInstance() {
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
    public LambdaQueryWrapper<SysRoleDeptDO> lambdaQuery(SysRoleDeptDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysRoleDeptDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(!ObjectUtils.isEmpty(entity.getRoleId()), SysRoleDeptDO::getRoleId, entity.getRoleId())
                .eq(!ObjectUtils.isEmpty(entity.getDeptId()), SysRoleDeptDO::getDeptId, entity.getDeptId())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysRoleDeptDO> lambdaUpdate(SysRoleDeptDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysRoleDeptDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
        ;
    }


}
