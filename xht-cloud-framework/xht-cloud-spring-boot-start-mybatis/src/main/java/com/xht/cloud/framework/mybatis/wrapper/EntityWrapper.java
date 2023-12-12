package com.xht.cloud.framework.mybatis.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xht.cloud.framework.mybatis.core.dataobject.AbstractDO;

import java.io.Serializable;

/**
 * 描述 ：Wrapper构建
 *
 * @author : 小糊涂
 **/
public interface EntityWrapper<T extends AbstractDO> extends Serializable {

    /**
     * 获取 QueryWrapper&lt;T&gt;
     *
     * @return QueryWrapper&lt;T&gt;
     */
    default QueryWrapper<T> query() {
        return new QueryWrapper<>();
    }

    /**
     * 获取 LambdaQueryWrapper&lt;T&gt;
     *
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    default LambdaQueryWrapper<T> lambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * 获取 LambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    LambdaQueryWrapper<T> lambdaQuery(T entity);

    /**
     * 获取 UpdateWrapper&lt;T&gt;
     *
     * @return UpdateWrapper&lt;T&gt;
     */
    default UpdateWrapper<T> update() {
        return new UpdateWrapper<>();
    }

    /**
     * 获取 LambdaUpdateWrapper&lt;T&gt;
     *
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    default LambdaUpdateWrapper<T> lambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }

    /**
     * 获取 LambdaUpdateWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    LambdaUpdateWrapper<T> lambdaUpdate(T entity);

}
