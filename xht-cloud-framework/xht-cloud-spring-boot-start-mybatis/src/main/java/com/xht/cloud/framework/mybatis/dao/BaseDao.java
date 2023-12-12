package com.xht.cloud.framework.mybatis.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;
import com.xht.cloud.framework.mybatis.mapper.BaseMapperX;
import com.xht.cloud.framework.mybatis.tool.SqlHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 描述 ：公共的dao接口
 *
 * @author : 小糊涂
 **/
public interface BaseDao<T extends AbstractDO> {

    /**
     * 获取对应 entity 的 BaseMapper
     *
     * @return BaseMapper
     */
    BaseMapperX<T> getBaseMapper();


    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    default boolean insert(T entity) {
        return SqlHelper.save(getBaseMapper().insert(entity));
    }

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    default boolean deleteById(Serializable id) {
        return SqlHelper.remove(getBaseMapper().deleteById(id));
    }

    /**
     * 根据 ID 删除
     *
     * @param ids 主键ID
     */
    default boolean deleteById(Collection<?> ids) {
        return SqlHelper.remove(getBaseMapper().deleteBatchIds(ids));
    }

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    default boolean delete(Wrapper<T> queryWrapper) {
        return SqlHelper.remove(getBaseMapper().delete(queryWrapper));
    }

    /**
     * 删除（根据ID或实体 批量删除）
     *
     * @param idList 主键ID列表或实体列表(不能为 null 以及 empty)
     */
    default boolean deleteBatchIds(Collection<?> idList) {
        return SqlHelper.remove(getBaseMapper().deleteBatchIds(idList));
    }

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param id            id
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    default boolean updateById(String id, UpdateWrapper<T> updateWrapper) {
        T t = getBaseMapper().selectById(id);
        if (Objects.isNull(t)) {
            return false;
        }
        return SqlHelper.update(getBaseMapper().update(t, updateWrapper));
    }

    /**
     * 根据id修改
     *
     * @param entity 实体对象封装操作类
     */
    default boolean updateById(T entity) {
        return SqlHelper.update(getBaseMapper().updateById(entity));
    }


    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    default Optional<T> selectById(Serializable id) {
        return Optional.ofNullable(getBaseMapper().selectById(id));
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    default List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return getBaseMapper().selectBatchIds(idList);
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Long selectCount(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectCount(queryWrapper);
    }

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default T selectOne(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectOne(queryWrapper);
    }

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default List<T> selectList(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default <P extends IPage<T>> P selectPage(P page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectPage(page, queryWrapper);
    }
}
