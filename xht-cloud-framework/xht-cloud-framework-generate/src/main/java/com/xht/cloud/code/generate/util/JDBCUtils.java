package com.xht.cloud.code.generate.util;

import com.xht.cloud.code.generate.exception.GenerateException;
import com.xht.cloud.code.generate.jdbc.JdbcProperties;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <h1>描述 ：JDBC工具类</h1>
 * <pre> </pre>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Slf4j
public class JDBCUtils {

    /**
     * 1.私有构造方法
     */
    private JDBCUtils() {
    }

    private static final int CONNECTION_TIMEOUTS_SECONDS = 6;

    /**
     * 获得数据库连接
     */
    public static Connection getConnection(JdbcProperties jdbcProperties) {
        try {
            DriverManager.setLoginTimeout(CONNECTION_TIMEOUTS_SECONDS);
            Class.forName(jdbcProperties.getDataBaseTypeEnums().getDriverClass());
            Connection connection = DriverManager.getConnection(jdbcProperties.getUrl(), jdbcProperties.getUserName(), jdbcProperties.getPassword());
            log.info("{} 连接成功 数据库类型:`{}` url:`{}`", jdbcProperties.getUserName(), jdbcProperties.getDataBaseTypeEnums(), jdbcProperties.getUrl());
            return connection;
        } catch (Exception e) {
            log.debug("连接出错了 e={}", e.getMessage(), e);
            throw new GenerateException("数据库链接失败");
        }
    }

    /**
     * 5.查询操作时所使用的的释放资源方法
     *
     * @param conn Connection
     * @param st   Statement
     * @param rs   ResultSet
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        close(conn, st);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 5.增删改操作时所使用的的释放资源方法
     *
     * @param conn Connection
     * @param st   Statement
     */
    public static void close(Connection conn, Statement st) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
