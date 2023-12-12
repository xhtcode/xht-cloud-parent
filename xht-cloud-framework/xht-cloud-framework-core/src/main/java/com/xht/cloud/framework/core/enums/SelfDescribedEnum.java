package com.xht.cloud.framework.core.enums;

/**
 * 描述 ：自定义枚举接口 - 扩展信息
 *
 * @author : 小糊涂
 **/
public interface SelfDescribedEnum {

    default String getName() {
        return name();
    }

    /**
     * @return 枚举 name
     */
    String name();

    /**
     * @return 描述
     */
    default String getDescription() {
        return name();
    }

}
