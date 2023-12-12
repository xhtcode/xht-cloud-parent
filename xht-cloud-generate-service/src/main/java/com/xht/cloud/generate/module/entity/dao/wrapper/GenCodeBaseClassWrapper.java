package com.xht.cloud.generate.module.entity.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.generate.module.entity.dao.dataobject.GenCodeBaseClassDO;
import java.util.Objects;

/**
 * 描述 ：代码生成器-基类
 *
 * @author : xht
 **/
public final class GenCodeBaseClassWrapper implements EntityWrapper<GenCodeBaseClassDO> {

    /**
     * 私有化构造器
     */
    private GenCodeBaseClassWrapper() {
    }

    /**
     * 获取实例
     */
    public static GenCodeBaseClassWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final GenCodeBaseClassWrapper wrapper;

        Instance() {
            wrapper = new GenCodeBaseClassWrapper();
        }

        public GenCodeBaseClassWrapper getInstance() {
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
    public LambdaQueryWrapper<GenCodeBaseClassDO> lambdaQuery(GenCodeBaseClassDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<GenCodeBaseClassDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), GenCodeBaseClassDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getClassName()), GenCodeBaseClassDO::getClassName, entity.getClassName())
                .eq(StringUtils.hasText(entity.getPackageName()), GenCodeBaseClassDO::getPackageName, entity.getPackageName())
                .eq(StringUtils.hasText(entity.getIgnoreField()), GenCodeBaseClassDO::getIgnoreField, entity.getIgnoreField())
                .eq(StringUtils.hasText(entity.getClassSort()), GenCodeBaseClassDO::getClassSort, entity.getClassSort())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<GenCodeBaseClassDO> lambdaUpdate(GenCodeBaseClassDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<GenCodeBaseClassDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(GenCodeBaseClassDO::getClassName, entity.getClassName())
                .set(GenCodeBaseClassDO::getPackageName, entity.getPackageName())
                .set(GenCodeBaseClassDO::getIgnoreField, entity.getIgnoreField())
                .set(GenCodeBaseClassDO::getClassSort, entity.getClassSort())
        ;
    }


}
