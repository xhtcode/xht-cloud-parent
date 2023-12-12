package com.xht.cloud.system.module.dict.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.dict.dao.dataobject.SysDictDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：字典
 *
 * @author : xht
 **/
public final class SysDictWrapper implements EntityWrapper<SysDictDO> {

    /**
     * 私有化构造器
     */
    private SysDictWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysDictWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<SysDictDO> lambdaQuery(SysDictDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysDictDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysDictDO::getId, entity.getId())
                .like(StringUtils.hasText(entity.getDictCode()), SysDictDO::getDictCode, entity.getDictCode())
                .like(StringUtils.hasText(entity.getDictValue()), SysDictDO::getDictValue, entity.getDictValue())
                .eq(StringUtils.hasText(entity.getDictStatus()), SysDictDO::getDictStatus, entity.getDictStatus())
                .eq(StringUtils.hasText(entity.getDescription()), SysDictDO::getDescription, entity.getDescription())
                .eq(!ObjectUtils.isEmpty(entity.getSortOrder()), SysDictDO::getSortOrder, entity.getSortOrder())
                ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysDictDO> lambdaUpdate(SysDictDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysDictDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysDictDO::getDictCode, entity.getDictCode())
                .set(SysDictDO::getDictValue, entity.getDictValue())
                .set(SysDictDO::getDictStatus, entity.getDictStatus())
                .set(SysDictDO::getDescription, entity.getDescription())
                .set(SysDictDO::getSortOrder, entity.getSortOrder())
                ;
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysDictWrapper wrapper;

        Instance() {
            wrapper = new SysDictWrapper();
        }

        public SysDictWrapper getInstance() {
            return wrapper;
        }
    }


}
