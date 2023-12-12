package com.xht.cloud.framework.mybatis.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import com.xht.cloud.framework.mybatis.dao.BaseDao;
import com.xht.cloud.framework.mybatis.tool.SqlHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 描述 ：基础dao实现类
 *
 * @author : 小糊涂
 **/
public abstract class DataScopeDaoImpl<T extends AbstractDO> implements BaseDao<T> {

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param id id
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    @Override
    public boolean updateById(String id, UpdateWrapper<T> updateWrapper) {
        T t = getBaseMapper().selectById(id);
        if (Objects.isNull(t)) {
            return false;
        }
        return SqlHelper.update(getBaseMapper().update(t, updateWrapper));
    }

    /**
     * 根据id修改
     * @param entity 实体对象封装操作类
     */
    @Override
    public boolean updateById(T entity) {
        return SqlHelper.update(getBaseMapper().updateById(entity));
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    @Override
    public Optional<T> selectById(Serializable id) {
        return Optional.ofNullable(getBaseMapper().selectById(id));
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return getBaseMapper().selectBatchIds(idList);
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    @Override
    public Long selectCount(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectCount(queryWrapper);
    }

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    @Override
    public <P extends IPage<T>> P selectPage(P page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectPage(page, queryWrapper);
    }
}
