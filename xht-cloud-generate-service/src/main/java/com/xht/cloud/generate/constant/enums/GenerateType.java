package com.xht.cloud.generate.constant.enums;

import com.xht.cloud.framework.core.enums.IEnum;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public enum GenerateType implements IEnum<String> {
    ZIP("0"),
    LOCALITY("1"),
    ;

    private final String value;

    GenerateType(String value) {
        this.value = value;
    }

    /**
     * 枚举值： vo返回 数据库存储字段
     *
     * @return 枚举值
     */
    @Override
    public String getValue() {
        return value;
    }
}
