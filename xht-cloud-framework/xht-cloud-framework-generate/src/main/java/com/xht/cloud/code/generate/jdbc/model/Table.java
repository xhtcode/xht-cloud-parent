package com.xht.cloud.code.generate.jdbc.model;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 描述 ：表信息
 *
 * @author : 小糊涂
 **/
public class Table {
    /**
     * 表所在实例
     */
    private String tableSchema;

    /**
     * 业务描述
     */
    private String tableName;

    /**
     * 驼峰表名
     */
    private String tableNameHump;

    /**
     * 大写字母驼峰表名
     */
    private String tableNameHumpUpperFirst;

    /**
     * 小写字母驼峰表名
     */
    private String tableNameHumpLowerFirst;

    /**
     * 数据库引擎
     */
    private String engine;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 表类型
     */
    private String tableType;

    /**
     * 字段
     */
    private List<Column> columns;

    /**
     * 表创建时间
     */
    private LocalDateTime createTime;

    /**
     * 表更新时间
     */
    private LocalDateTime updateTime;

    public String getTableSchema() {
        return tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableNameHump() {
        return tableNameHump;
    }

    public String getTableNameHumpUpperFirst() {
        return tableNameHumpUpperFirst;
    }

    public String getTableNameHumpLowerFirst() {
        return tableNameHumpLowerFirst;
    }

    public String getEngine() {
        return engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getTableType() {
        return tableType;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.tableNameHump = StrUtil.toCamelCase(tableName);
        this.tableNameHumpUpperFirst = StrUtil.upperFirst(this.tableNameHump);
        this.tableNameHumpLowerFirst = StrUtil.lowerFirst(this.tableNameHump);
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = StrUtil.replace(tableComment, "表", "");
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isView() {
        return "VIEW".equals(tableType);
    }
}
