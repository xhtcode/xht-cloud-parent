package com.xht.cloud.system.module.dept.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.dept.dao.dataobject.SysPositionDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：岗位信息
 *
 * @author : xht
 **/
public final class SysPositionWrapper implements EntityWrapper<SysPositionDO> {

    /**
     * 私有化构造器
     */
    private SysPositionWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysPositionWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysPositionWrapper wrapper;

        Instance() {
            wrapper = new SysPositionWrapper();
        }

        public SysPositionWrapper getInstance() {
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
    public LambdaQueryWrapper<SysPositionDO> lambdaQuery(SysPositionDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysPositionDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysPositionDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getParentId()), SysPositionDO::getParentId, entity.getParentId())
                .eq(StringUtils.hasText(entity.getDeptId()), SysPositionDO::getDeptId, entity.getDeptId())
                .eq(StringUtils.hasText(entity.getPositionCode()), SysPositionDO::getPositionCode, entity.getPositionCode())
                .eq(StringUtils.hasText(entity.getPositionName()), SysPositionDO::getPositionName, entity.getPositionName())
                .eq(!ObjectUtils.isEmpty(entity.getSort()), SysPositionDO::getSort, entity.getSort())
                .eq(!ObjectUtils.isEmpty(entity.getStatus()), SysPositionDO::getStatus, entity.getStatus())
                .eq(StringUtils.hasText(entity.getDescription()), SysPositionDO::getDescription, entity.getDescription())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysPositionDO> lambdaUpdate(SysPositionDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysPositionDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysPositionDO::getParentId, entity.getParentId())
                .set(SysPositionDO::getDeptId, entity.getDeptId())
                .set(SysPositionDO::getPositionCode, entity.getPositionCode())
                .set(SysPositionDO::getPositionName, entity.getPositionName())
                .set(SysPositionDO::getSort, entity.getSort())
                .set(SysPositionDO::getStatus, entity.getStatus())
                .set(SysPositionDO::getDescription, entity.getDescription())
        ;
    }


}
