package com.xht.cloud.code.generate.constant;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public enum RequestConstant {

    BASE("","公共请求信息"),
    ADD("Add","增加请求信息"),
    QUERY("Query","查询请求信息"),
    UPDATE("Update","修改请求信息");

    private final String name;
    private final String desc;

    RequestConstant(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * 类别
     */
    public String getName() {
        return name;
    }

    /**
     * 描述
     */
    public String getDesc() {
        return desc;
    }
}
