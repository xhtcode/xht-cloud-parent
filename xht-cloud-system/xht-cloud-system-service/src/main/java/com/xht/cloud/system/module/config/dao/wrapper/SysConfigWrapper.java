package com.xht.cloud.system.module.config.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.config.dao.dataobject.SysConfigDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：系统配置信息
 *
 * @author : xht
 **/
public final class SysConfigWrapper implements EntityWrapper<SysConfigDO> {

    /**
     * 私有化构造器
     */
    private SysConfigWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysConfigWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysConfigWrapper wrapper;

        Instance() {
            wrapper = new SysConfigWrapper();
        }

        public SysConfigWrapper getInstance() {
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
    public LambdaQueryWrapper<SysConfigDO> lambdaQuery(SysConfigDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysConfigDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysConfigDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getConfigCode()), SysConfigDO::getConfigCode, entity.getConfigCode())
                .eq(StringUtils.hasText(entity.getConfigName()), SysConfigDO::getConfigName, entity.getConfigName())
                .eq(StringUtils.hasText(entity.getConfigInfo()), SysConfigDO::getConfigInfo, entity.getConfigInfo())
                .eq(StringUtils.hasText(entity.getClassName()), SysConfigDO::getClassName, entity.getClassName())
                .eq(StringUtils.hasText(entity.getDescription()), SysConfigDO::getDescription, entity.getDescription())
                .eq(!ObjectUtils.isEmpty(entity.getStatus()), SysConfigDO::getStatus, entity.getStatus())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysConfigDO> lambdaUpdate(SysConfigDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysConfigDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysConfigDO::getConfigCode, entity.getConfigCode())
                .set(SysConfigDO::getConfigName, entity.getConfigName())
                .set(SysConfigDO::getConfigInfo, entity.getConfigInfo())
                .set(SysConfigDO::getClassName, entity.getClassName())
                .set(SysConfigDO::getDescription, entity.getDescription())
                .set(SysConfigDO::getStatus, entity.getStatus())
        ;
    }


}
