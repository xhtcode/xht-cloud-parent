package com.xht.cloud.code.generate.jdbc;

import com.xht.cloud.code.generate.jdbc.enums.DataBaseTypeEnums;
import com.xht.cloud.code.generate.jdbc.model.Column;
import com.xht.cloud.code.generate.jdbc.model.Table;

import java.util.List;

/**
 * 获取数据库信息
 *
 * @author : 小糊涂
 **/
public interface IDataBaseQuery {

    /**
     * 获取表信息
     *
     * @param jdbcProperties 数据库链接
     * @param tableName      表名称
     * @return 表信息 一个表名对应一个表的信息
     */
    List<Table> info(JdbcProperties jdbcProperties, String... tableName);


    /**
     * 数据库类型选择
     *
     * @param dbType 数据库类型
     * @return Boolean
     */
    Boolean support(DataBaseTypeEnums dbType);

}
