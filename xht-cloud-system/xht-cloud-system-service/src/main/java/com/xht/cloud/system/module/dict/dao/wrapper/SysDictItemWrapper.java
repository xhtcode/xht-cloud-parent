package com.xht.cloud.system.module.dict.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.dict.dao.dataobject.SysDictItemDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：字典数据表
 *
 * @author : xht
 **/
public final class SysDictItemWrapper implements EntityWrapper<SysDictItemDO> {

    /**
     * 私有化构造器
     */
    private SysDictItemWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysDictItemWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<SysDictItemDO> lambdaQuery(SysDictItemDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysDictItemDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysDictItemDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getDictId()), SysDictItemDO::getDictId, entity.getDictId())
                .like(StringUtils.hasText(entity.getDictCode()), SysDictItemDO::getDictCode, entity.getDictCode())
                .like(StringUtils.hasText(entity.getDictValue()), SysDictItemDO::getDictValue, entity.getDictValue())
                .eq(!ObjectUtils.isEmpty(entity.getSortOrder()), SysDictItemDO::getSortOrder, entity.getSortOrder())
                .eq(StringUtils.hasText(entity.getStatus()), SysDictItemDO::getStatus, entity.getStatus())
                .eq(StringUtils.hasText(entity.getDescription()), SysDictItemDO::getDescription, entity.getDescription())
                ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysDictItemDO> lambdaUpdate(SysDictItemDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysDictItemDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysDictItemDO::getDictId, entity.getDictId())
                .set(SysDictItemDO::getDictCode, entity.getDictCode())
                .set(SysDictItemDO::getDictValue, entity.getDictValue())
                .set(SysDictItemDO::getSortOrder, entity.getSortOrder())
                .set(SysDictItemDO::getStatus, entity.getStatus())
                .set(SysDictItemDO::getDescription, entity.getDescription())
                ;
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysDictItemWrapper wrapper;

        Instance() {
            wrapper = new SysDictItemWrapper();
        }

        public SysDictItemWrapper getInstance() {
            return wrapper;
        }
    }


}
