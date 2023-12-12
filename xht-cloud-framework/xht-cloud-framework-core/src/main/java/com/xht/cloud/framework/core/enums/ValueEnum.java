package com.xht.cloud.framework.core.enums;

import java.io.Serializable;

/**
 * 描述 ：自定义枚举接口
 *
 * @author : 小糊涂
 **/
public interface ValueEnum<T extends Serializable> {

    /**
     * 枚举值： vo返回 数据库存储字段
     *
     * @return 枚举值
     */
    T getValue();

}
