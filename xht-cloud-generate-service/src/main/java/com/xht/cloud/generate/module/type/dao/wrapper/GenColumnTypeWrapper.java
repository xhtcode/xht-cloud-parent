package com.xht.cloud.generate.module.type.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.generate.module.type.dao.dataobject.GenColumnTypeDO;
import java.util.Objects;

/**
 * 描述 ：代码生成器-字段类型对应
 *
 * @author : xht
 **/
public final class GenColumnTypeWrapper implements EntityWrapper<GenColumnTypeDO> {

    /**
     * 私有化构造器
     */
    private GenColumnTypeWrapper() {
    }

    /**
     * 获取实例
     */
    public static GenColumnTypeWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final GenColumnTypeWrapper wrapper;

        Instance() {
            wrapper = new GenColumnTypeWrapper();
        }

        public GenColumnTypeWrapper getInstance() {
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
    public LambdaQueryWrapper<GenColumnTypeDO> lambdaQuery(GenColumnTypeDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<GenColumnTypeDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), GenColumnTypeDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getDbLabel()), GenColumnTypeDO::getDbLabel, entity.getDbLabel())
                .eq(StringUtils.hasText(entity.getDbValue()), GenColumnTypeDO::getDbValue, entity.getDbValue())
                .eq(StringUtils.hasText(entity.getLabel()), GenColumnTypeDO::getLabel, entity.getLabel())
                .eq(StringUtils.hasText(entity.getValue()), GenColumnTypeDO::getValue, entity.getValue())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<GenColumnTypeDO> lambdaUpdate(GenColumnTypeDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<GenColumnTypeDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(GenColumnTypeDO::getDbLabel, entity.getDbLabel())
                .set(GenColumnTypeDO::getDbValue, entity.getDbValue())
                .set(GenColumnTypeDO::getLabel, entity.getLabel())
                .set(GenColumnTypeDO::getValue, entity.getValue())
        ;
    }


}
