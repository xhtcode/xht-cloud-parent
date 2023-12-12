package com.xht.cloud.system.module.area.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.area.dao.dataobject.SysAreaInfoDO;

import java.util.Objects;

/**
 * 描述 ：地区信息
 *
 * @author : xht
 **/
public final class SysAreaInfoWrapper implements EntityWrapper<SysAreaInfoDO> {

    /**
     * 私有化构造器
     */
    private SysAreaInfoWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysAreaInfoWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysAreaInfoWrapper wrapper;

        Instance() {
            wrapper = new SysAreaInfoWrapper();
        }

        public SysAreaInfoWrapper getInstance() {
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
    public LambdaQueryWrapper<SysAreaInfoDO> lambdaQuery(SysAreaInfoDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysAreaInfoDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysAreaInfoDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getParentId()), SysAreaInfoDO::getParentId, entity.getParentId())
                .like(StringUtils.hasText(entity.getName()), SysAreaInfoDO::getName, entity.getName())
                .eq(StringUtils.hasText(entity.getLevel()), SysAreaInfoDO::getLevel, entity.getLevel())
                .like(StringUtils.hasText(entity.getAreaNo()), SysAreaInfoDO::getAreaNo, entity.getAreaNo())
                .eq(StringUtils.hasText(entity.getCategory()), SysAreaInfoDO::getCategory, entity.getCategory())
                .eq(StringUtils.hasText(entity.getMsg()), SysAreaInfoDO::getMsg, entity.getMsg())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysAreaInfoDO> lambdaUpdate(SysAreaInfoDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysAreaInfoDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysAreaInfoDO::getParentId, entity.getParentId())
                .set(SysAreaInfoDO::getName, entity.getName())
                .set(SysAreaInfoDO::getLevel, entity.getLevel())
                .set(SysAreaInfoDO::getAreaNo, entity.getAreaNo())
                .set(SysAreaInfoDO::getCategory, entity.getCategory())
                .set(SysAreaInfoDO::getMsg, entity.getMsg())
        ;
    }


}
