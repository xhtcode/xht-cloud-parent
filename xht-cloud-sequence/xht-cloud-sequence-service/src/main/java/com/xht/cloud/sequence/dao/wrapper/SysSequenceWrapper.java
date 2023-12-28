package com.xht.cloud.sequence.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.sequence.dao.dataobject.SysSequenceDO;

/**
 * 描述 ：序列生成器
 *
 * @author : 小糊涂
 **/
public class SysSequenceWrapper implements EntityWrapper<SysSequenceDO> {
    /**
     * 私有化构造器
     */
    private SysSequenceWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysSequenceWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysSequenceWrapper wrapper;

        Instance() {
            wrapper = new SysSequenceWrapper();
        }

        public SysSequenceWrapper getInstance() {
            return wrapper;
        }
    }
    /**
     * 获取 {@link LambdaQueryWrapper }
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper }
     */
    @Override
    public LambdaQueryWrapper<SysSequenceDO> lambdaQuery(SysSequenceDO entity) {
        return null;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper }
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper }
     */
    @Override
    public LambdaUpdateWrapper<SysSequenceDO> lambdaUpdate(SysSequenceDO entity) {
        return null;
    }
}
