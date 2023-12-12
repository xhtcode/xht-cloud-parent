package com.xht.cloud.generate.constant.enums;

import java.util.Arrays;

/**
 * <h1>描述 ：数据库类型</h1>
 * <pre> </pre>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
public enum DataBaseType {

    MySQL("MySQL", "com.mysql.cj.jdbc.Driver"),

    Oracle("Oracle", "oracle.jdbc.driver.OracleDriver");

    private final String dbType;

    private final String driverClass;

    DataBaseType(String dbType, String driverClass) {
        this.dbType = dbType;
        this.driverClass = driverClass;
    }

    public static DataBaseType getByDbType(String dbType) {
        return Arrays.stream(values()).filter(item -> item.dbType.toLowerCase().equals(dbType.toLowerCase())).findFirst().orElse(null);
    }

    public String getDbType() {
        return dbType;
    }

    public String getDriverClass() {
        return driverClass;
    }
}
