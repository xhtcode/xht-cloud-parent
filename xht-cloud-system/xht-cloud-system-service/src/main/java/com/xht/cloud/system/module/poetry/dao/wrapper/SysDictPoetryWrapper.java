package com.xht.cloud.system.module.poetry.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.poetry.dao.dataobject.SysDictPoetryDO;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : xht
 **/
public final class SysDictPoetryWrapper implements EntityWrapper<SysDictPoetryDO> {

    /**
     * 私有化构造器
     */
    private SysDictPoetryWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysDictPoetryWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysDictPoetryWrapper wrapper;

        Instance() {
            wrapper = new SysDictPoetryWrapper();
        }

        public SysDictPoetryWrapper getInstance() {
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
    public LambdaQueryWrapper<SysDictPoetryDO> lambdaQuery(SysDictPoetryDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysDictPoetryDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysDictPoetryDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getAuthor()), SysDictPoetryDO::getAuthor, entity.getAuthor())
                .eq(StringUtils.hasText(entity.getAuthorDesc()), SysDictPoetryDO::getAuthorDesc, entity.getAuthorDesc())
                .eq(StringUtils.hasText(entity.getType()), SysDictPoetryDO::getType, entity.getType())
                .eq(StringUtils.hasText(entity.getTypeDesc()), SysDictPoetryDO::getTypeDesc, entity.getTypeDesc())
                .eq(StringUtils.hasText(entity.getTitle()), SysDictPoetryDO::getTitle, entity.getTitle())
                .eq(StringUtils.hasText(entity.getTilteDesc()), SysDictPoetryDO::getTilteDesc, entity.getTilteDesc())
                .eq(StringUtils.hasText(entity.getContext()), SysDictPoetryDO::getContext, entity.getContext())
                .eq(StringUtils.hasText(entity.getContextDesc()), SysDictPoetryDO::getContextDesc, entity.getContextDesc())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysDictPoetryDO> lambdaUpdate(SysDictPoetryDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysDictPoetryDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysDictPoetryDO::getAuthor, entity.getAuthor())
                .set(SysDictPoetryDO::getAuthorDesc, entity.getAuthorDesc())
                .set(SysDictPoetryDO::getType, entity.getType())
                .set(SysDictPoetryDO::getTypeDesc, entity.getTypeDesc())
                .set(SysDictPoetryDO::getTitle, entity.getTitle())
                .set(SysDictPoetryDO::getTilteDesc, entity.getTilteDesc())
                .set(SysDictPoetryDO::getContext, entity.getContext())
                .set(SysDictPoetryDO::getContextDesc, entity.getContextDesc())
        ;
    }


}
