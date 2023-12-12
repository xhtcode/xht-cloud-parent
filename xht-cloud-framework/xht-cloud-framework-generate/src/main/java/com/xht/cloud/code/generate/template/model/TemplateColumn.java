package com.xht.cloud.code.generate.template.model;

import lombok.Builder;
import lombok.Data;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@Builder
public class TemplateColumn {

    /**
     * 1主键 0不是主键
     */
    private String pk;
    /**
     * 字段名称
     */
    private String columnName;
    private String columnNameUpperFirst;

    /**
     * 字段名称 数据库
     */
    private String columnNameDb;


    /**
     * 字段名称 get
     */
    private String columnNameGet;

    /**
     * 字段名称 set
     */
    private String columnNameSet;

    private String tsType;
    private String javaType;

    private String javaTypeClass;

    /**
     * 默认值
     */
    private String columnDefault;

    /**
     * 字段描述
     */
    private String describe;

    /**
     * 是否为空 true 不为空
     */
    private boolean isNullable;

    /**
     * 字段长度
     */
    private int length;

    private String convertMethod;

    /**
     * 冗余字段
     */
    private String redundancy;

}
