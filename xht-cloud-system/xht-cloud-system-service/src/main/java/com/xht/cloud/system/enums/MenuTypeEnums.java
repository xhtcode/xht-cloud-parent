package com.xht.cloud.system.enums;


import com.xht.cloud.framework.core.enums.IEnum;

/**
 * <h1>描述 ：菜单类型</h1>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
public enum MenuTypeEnums implements IEnum<String>, com.baomidou.mybatisplus.annotation.IEnum<String> {

    M("M", "目录"),
    C("C", "菜单"),
    B("B", "按钮"),
    ;


    private final String value;

    private final String desc;


    MenuTypeEnums(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    /**
     * @return 描述
     */
    @Override
    public String getDescription() {
        return desc;
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
