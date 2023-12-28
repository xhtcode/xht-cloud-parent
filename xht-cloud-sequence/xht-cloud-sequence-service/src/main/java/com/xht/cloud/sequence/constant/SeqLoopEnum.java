package com.xht.cloud.sequence.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@AllArgsConstructor
public enum SeqLoopEnum implements IEnum<String>, com.xht.cloud.framework.core.enums.IEnum<String> {

    LOOP("0"),//"循环",

    NO_LOOP("1");//"不循环",

    @JsonValue    //标记响应json值
    @EnumValue   //标记数据库存的值是code
    private final String value;

    @Override
    public String getValue() {
        return this.value;
    }
}
