package com.xht.cloud.framework.security.constant;

import com.xht.cloud.framework.core.enums.IEnum;

/**
 * 描述 ：用户类型
 *
 * @author : 小糊涂
 **/
public enum UserTypeEnums implements IEnum<String> {

    ADMIN("0", "系统管理员"),
    SYSTEM_USER("1", "系统管理员"),

    USER("2", "用户"),

    SHOP_USER("3", "商家");

    private final String value;

    private final String desc;


    UserTypeEnums(String value, String desc) {
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
