package com.xht.cloud.code.generate.jdbc.enums;

/**
 * 数据库类型
 *
 * @author : 小糊涂
 **/
public enum DataBaseTypeEnums {

    MySQL("MySQL", "com.mysql.cj.jdbc.Driver"),

    Oracle("Oracle", "oracle.jdbc.driver.OracleDriver");

    private final String dbType;

    private final String driverClass;

    DataBaseTypeEnums(String dbType, String driverClass) {
        this.dbType = dbType;
        this.driverClass = driverClass;
    }

    public String getDbType() {
        return dbType;
    }

    public String getDriverClass() {
        return driverClass;
    }

    @Override
    public String toString() {
        return dbType;
    }
}
