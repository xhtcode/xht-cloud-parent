package com.xht.cloud.generate.module.template.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.generate.module.template.dao.dataobject.GenCodeGroupDO;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : xht
 **/
public final class GenCodeGroupWrapper implements EntityWrapper<GenCodeGroupDO> {

    /**
     * 私有化构造器
     */
    private GenCodeGroupWrapper() {
    }

    /**
     * 获取实例
     */
    public static GenCodeGroupWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final GenCodeGroupWrapper wrapper;

        Instance() {
            wrapper = new GenCodeGroupWrapper();
        }

        public GenCodeGroupWrapper getInstance() {
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
    public LambdaQueryWrapper<GenCodeGroupDO> lambdaQuery(GenCodeGroupDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<GenCodeGroupDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), GenCodeGroupDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getGroupName()), GenCodeGroupDO::getGroupName, entity.getGroupName())
                .eq(StringUtils.hasText(entity.getGroupDesc()), GenCodeGroupDO::getGroupDesc, entity.getGroupDesc())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<GenCodeGroupDO> lambdaUpdate(GenCodeGroupDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<GenCodeGroupDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(GenCodeGroupDO::getGroupName, entity.getGroupName())
                .set(GenCodeGroupDO::getGroupDesc, entity.getGroupDesc())
        ;
    }


}
