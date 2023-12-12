package com.xht.cloud.generate.constant.enums;

import com.xht.cloud.framework.core.enums.IEnum;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public enum QueryTypeEnum implements IEnum<String> {
    eq("eq"),
    ne("ne"),
    gt("gt"),
    ge("ge"),
    lt("lt"),
    le("le"),
    like("like"),
    leftLike("leftLike"),
    rightLike("rightLike"),
    ;

    private final String value;

    QueryTypeEnum(String value) {
        this.value = value;
    }

    /**
     * 枚举值： vo返回 数据库存储字段
     *
     * @return 枚举值
     */
    @Override
    public String getValue() {
        return this.value;
    }
}
