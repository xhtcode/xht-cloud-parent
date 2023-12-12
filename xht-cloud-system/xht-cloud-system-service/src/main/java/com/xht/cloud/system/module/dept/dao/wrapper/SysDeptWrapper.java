package com.xht.cloud.system.module.dept.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.dept.dao.dataobject.SysDeptDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：部门
 *
 * @author : xht
 **/
public final class SysDeptWrapper implements EntityWrapper<SysDeptDO> {

    /**
     * 私有化构造器
     */
    private SysDeptWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysDeptWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysDeptWrapper wrapper;

        Instance() {
            wrapper = new SysDeptWrapper();
        }

        public SysDeptWrapper getInstance() {
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
    public LambdaQueryWrapper<SysDeptDO> lambdaQuery(SysDeptDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysDeptDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysDeptDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getParentId()), SysDeptDO::getParentId, entity.getParentId())
                .eq(StringUtils.hasText(entity.getDirectorId()), SysDeptDO::getDirectorId, entity.getDirectorId())
                .eq(StringUtils.hasText(entity.getDeptName()), SysDeptDO::getDeptName, entity.getDeptName())
                .eq(StringUtils.hasText(entity.getDeptCode()), SysDeptDO::getDeptCode, entity.getDeptCode())
                .eq(StringUtils.hasText(entity.getDeptType()), SysDeptDO::getDeptType, entity.getDeptType())
                .eq(StringUtils.hasText(entity.getDeptLeader()), SysDeptDO::getDeptLeader, entity.getDeptLeader())
                .eq(StringUtils.hasText(entity.getDeptTel()), SysDeptDO::getDeptTel, entity.getDeptTel())
                .eq(!ObjectUtils.isEmpty(entity.getDeptSort()), SysDeptDO::getDeptSort, entity.getDeptSort())
                .eq(StringUtils.hasText(entity.getDeptStatus()), SysDeptDO::getDeptStatus, entity.getDeptStatus())
                .eq(StringUtils.hasText(entity.getDescription()), SysDeptDO::getDescription, entity.getDescription())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysDeptDO> lambdaUpdate(SysDeptDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysDeptDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysDeptDO::getParentId, entity.getParentId())
                .set(SysDeptDO::getDirectorId, entity.getDirectorId())
                .set(SysDeptDO::getDeptName, entity.getDeptName())
                .set(SysDeptDO::getDeptCode, entity.getDeptCode())
                .set(SysDeptDO::getDeptType, entity.getDeptType())
                .set(SysDeptDO::getDeptLeader, entity.getDeptLeader())
                .set(SysDeptDO::getDeptTel, entity.getDeptTel())
                .set(SysDeptDO::getDeptSort, entity.getDeptSort())
                .set(SysDeptDO::getDeptStatus, entity.getDeptStatus())
                .set(SysDeptDO::getDescription, entity.getDescription())
        ;
    }


}
