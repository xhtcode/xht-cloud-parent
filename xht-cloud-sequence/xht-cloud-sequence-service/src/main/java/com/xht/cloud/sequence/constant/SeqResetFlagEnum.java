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
public enum SeqResetFlagEnum implements IEnum<String>, com.xht.cloud.framework.core.enums.IEnum<String> {
    NO_RESET("0"),//不重置
    DAY("1"),//每天
    MONTH("2"),//月
    YEAR("4");//年


    @JsonValue    //标记响应json值
    @EnumValue   //标记数据库存的值是code
    private final String value;

    @Override
    public String getValue() {
        return this.value;
    }
}
