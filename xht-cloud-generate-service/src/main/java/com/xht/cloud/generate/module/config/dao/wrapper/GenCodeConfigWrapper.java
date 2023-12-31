package com.xht.cloud.generate.module.config.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.generate.module.config.dao.dataobject.GenCodeConfigDO;
import java.util.Objects;
import org.springframework.util.ObjectUtils;

/**
 * 描述 ：代码生成器-配置中心
 *
 * @author : xht
 **/
public final class GenCodeConfigWrapper implements EntityWrapper<GenCodeConfigDO> {

    /**
     * 私有化构造器
     */
    private GenCodeConfigWrapper() {
    }

    /**
     * 获取实例
     */
    public static GenCodeConfigWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final GenCodeConfigWrapper wrapper;

        Instance() {
            wrapper = new GenCodeConfigWrapper();
        }

        public GenCodeConfigWrapper getInstance() {
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
    public LambdaQueryWrapper<GenCodeConfigDO> lambdaQuery(GenCodeConfigDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<GenCodeConfigDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), GenCodeConfigDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getConfigName()), GenCodeConfigDO::getConfigName, entity.getConfigName())
                .eq(StringUtils.hasText(entity.getConfigDesc()), GenCodeConfigDO::getConfigDesc, entity.getConfigDesc())
                .eq(!ObjectUtils.isEmpty(entity.getConfigSort()), GenCodeConfigDO::getConfigSort, entity.getConfigSort())
                .eq(StringUtils.hasText(entity.getConfigInfo()), GenCodeConfigDO::getConfigInfo, entity.getConfigInfo())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<GenCodeConfigDO> lambdaUpdate(GenCodeConfigDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<GenCodeConfigDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(GenCodeConfigDO::getConfigName, entity.getConfigName())
                .set(GenCodeConfigDO::getConfigDesc, entity.getConfigDesc())
                .set(GenCodeConfigDO::getConfigSort, entity.getConfigSort())
                .set(GenCodeConfigDO::getConfigInfo, entity.getConfigInfo())
        ;
    }


}
