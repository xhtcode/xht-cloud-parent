package com.xht.cloud.framework.mybatis.core.enums;

import com.xht.cloud.framework.core.enums.IEnum;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public enum DataScopeTypeEnums implements IEnum<String> {
    DEPT_USER_TYPE("部门级用户权限");


    private final String value;

    DataScopeTypeEnums(String value) {
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
