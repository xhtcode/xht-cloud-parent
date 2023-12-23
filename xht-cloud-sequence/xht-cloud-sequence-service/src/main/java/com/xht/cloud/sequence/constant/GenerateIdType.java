package com.xht.cloud.sequence.constant;

import com.xht.cloud.framework.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@AllArgsConstructor
public enum GenerateIdType implements IEnum<Integer> {

    UUID(1001, "UUID"),

    SNOWFLAKE(1002, "SnowFlake");

    private final Integer value;

    @Getter
    private final String desc;

    /**
     * 枚举值： vo返回 数据库存储字段
     *
     * @return 枚举值
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
