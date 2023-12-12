package com.xht.cloud.code.generate.jdbc.database;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.xht.cloud.code.generate.exception.GenerateException;
import com.xht.cloud.code.generate.jdbc.IDataBaseQuery;
import com.xht.cloud.code.generate.jdbc.JdbcProperties;
import com.xht.cloud.code.generate.jdbc.enums.DataBaseTypeEnums;
import com.xht.cloud.code.generate.jdbc.model.Column;
import com.xht.cloud.code.generate.jdbc.model.Table;
import com.xht.cloud.code.generate.util.JDBCUtils;
import com.xht.cloud.code.generate.util.SqlUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 描述 ：mysql 数据库表信息，字段信息查询
 *
 * @author : 小糊涂
 **/
@Slf4j
public class MySqlDataBaseQueryImpl implements IDataBaseQuery {

    private final String tableInfoSql = "select table_schema , table_name, engine , table_comment , create_time , update_time , table_type from information_schema.tables where table_schema =( select database ()) and table_name in (%s) order by create_time desc, update_time desc";
    private final String columnInfoSql = "select column_name, column_default, column_comment, is_nullable, column_key, ordinal_position as sort, data_type, character_maximum_length as column_length from information_schema.columns where table_schema =( select database ()) and table_name = ? order by ordinal_position";

    /**
     * 获取表信息
     *
     * @param jdbcProperties 数据库链接
     * @param tableName      业务描述
     * @return 表信息 一个表名对应一个表的信息
     */
    @Override
    public List<Table> info(JdbcProperties jdbcProperties, String... tableName) {
        Connection connection = JDBCUtils.getConnection(jdbcProperties);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Table> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(String.format(tableInfoSql, SqlUtils.convertIn(tableName)));
            for (int i = 0; i < tableName.length; i++) {
                preparedStatement.setString(i + 1, tableName[i]);
            }
            resultSet = getTable(preparedStatement, result);
            for (Table table : result) {
                table.setColumns(info(connection, table.getTableName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenerateException(e.getMessage());
        } finally {
            JDBCUtils.close(connection, preparedStatement, resultSet);
        }
        return result;
    }

    /**
     * 获取字段信息
     *
     * @param connection 数据库链接
     * @param tableName  业务描述
     * @return 字段信息
     */
    public List<Column> info(Connection connection, String tableName) {
        PreparedStatement preparedStatement = null;
        List<Column> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(columnInfoSql);
            preparedStatement.setString(1, tableName);
            getColumns(preparedStatement, result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenerateException(e.getMessage());
        }finally {
            JDBCUtils.close(preparedStatement);
        }
        return result;
    }

    private void getColumns(PreparedStatement preparedStatement, List<Column> result) throws SQLException {
        Props props = new Props("gen.properties");
        log.trace("查询字段信息sql:{}", preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        Column entity;
        while (resultSet.next()) {
            entity = new Column();
            entity.setColumnName(resultSet.getString("column_name"));
            entity.setColumnDefault(Convert.toStr(resultSet.getString("column_default"),""));
            entity.setColumnComment(StrUtil.cleanBlank(Convert.toStr(resultSet.getString("column_comment"))));
            entity.setIsNullable(String.valueOf(resultSet.getString("is_nullable").equals("NO")));
            entity.setColumnKey(resultSet.getString("column_key"));
            entity.setSort(Convert.toInt(resultSet.getString("sort"),0));
            entity.setLength(Convert.toInt(resultSet.getString("column_length"),0));
            entity.setDbType(resultSet.getString("data_type"));
            entity.setJavaType(props.getStr(resultSet.getString("data_type")));
            result.add(entity);
        }
        JDBCUtils.close(resultSet);
    }

    private ResultSet getTable(PreparedStatement preparedStatement, List<Table> result) throws SQLException {
        log.trace("查询表信息sql:{}", preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        Table entity;
        while (resultSet.next()) {
            entity = new Table();
            entity.setTableSchema(resultSet.getString("table_schema"));
            entity.setTableName(resultSet.getString("table_name"));
            entity.setEngine(resultSet.getString("engine"));
            entity.setTableComment(resultSet.getString("table_comment"));
            entity.setTableType(resultSet.getString("table_type"));
            entity.setCreateTime(Convert.toLocalDateTime(resultSet.getString("create_time")));
            entity.setUpdateTime(Convert.toLocalDateTime(resultSet.getString("update_time")));
            result.add(entity);
        }
        return resultSet;
    }

    /**
     * 数据库类型选择
     *
     * @param dbType 数据库类型
     * @return Boolean
     */
    @Override
    public Boolean support(DataBaseTypeEnums dbType) {
        return Objects.equals(DataBaseTypeEnums.MySQL, dbType);
    }
}
