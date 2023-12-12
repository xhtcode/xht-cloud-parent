package com.xht.cloud.code.generate.jdbc.model;

import cn.hutool.core.util.StrUtil;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class Column {

    /**
     * 字段名称
     */
    private String columnName;


    private String columnNameHump;

    private String columnNameHumpUpperFirst;

    private String columnNameHumpLowerFirst;

    /**
     * 默认值
     */
    private String columnDefault;

    /**
     * 字段描述
     */
    private String columnComment;

    /**
     * 是否为空
     */
    private String isNullable;

    /**
     * 该列是否已建立索引
     * <p>
     * 如果COLUMN_KEY为空，则该列要么不被索引，要么仅被索引为多列非唯一索引中的第二列。
     * <p>
     * 如果COLUMN_KEY为is PRI，PRIMARY KEY则列为a或为多列中的列之一PRIMARY KEY。
     * <p>
     * 如果COLUMN_KEY为 UNI，则该列为UNIQUE索引的第一列。(一个 UNIQUE索引允许多个 NULL值，但是您可以NULL通过检查该 Null列来判断该列是否允许。)
     * <p>
     * 如果COLUMN_KEY为is MUL，则该列是非唯一索引的第一列，在该列中允许多次出现给定值。
     */
    private String columnKey;

    private String pk;

    /**
     * 排序
     */
    private int sort;

    /**
     * 字段长度
     */
    private int length;

    private String javaType;

    private String dbType;

    public String getColumnName() {
        return columnName;
    }

    public String getColumnNameHump() {
        return columnNameHump;
    }

    public String getColumnNameHumpUpperFirst() {
        return columnNameHumpUpperFirst;
    }

    public String getColumnNameHumpLowerFirst() {
        return columnNameHumpLowerFirst;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public int getSort() {
        return sort;
    }

    public int getLength() {
        return length;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        this.columnNameHump = StrUtil.toCamelCase(columnName);
        this.columnNameHumpUpperFirst = StrUtil.upperFirst(this.columnNameHump);
        this.columnNameHumpLowerFirst = StrUtil.lowerFirst(this.columnNameHump);
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getPk() {
        return columnKey.contains("PRI") ? "1" : "0";
    }
}
