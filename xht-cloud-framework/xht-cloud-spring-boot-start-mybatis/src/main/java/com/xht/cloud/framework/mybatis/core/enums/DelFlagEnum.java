package com.xht.cloud.framework.mybatis.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public enum DelFlagEnum implements IEnum<String> {

    NORMAL("0"),//"未删除",

    DELETE("1");//"已删除",

    @JsonValue    //标记响应json值
    @EnumValue//标记数据库存的值是code
    private final String value;

    DelFlagEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
