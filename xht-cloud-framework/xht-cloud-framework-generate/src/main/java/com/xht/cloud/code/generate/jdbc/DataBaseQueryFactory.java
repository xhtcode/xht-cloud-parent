package com.xht.cloud.code.generate.jdbc;

import com.xht.cloud.code.generate.exception.GenerateException;
import com.xht.cloud.code.generate.jdbc.database.MySqlDataBaseQueryImpl;
import com.xht.cloud.code.generate.jdbc.enums.DataBaseTypeEnums;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库查询工厂
 *
 * @author : 小糊涂
 **/
public class DataBaseQueryFactory {

    private static final List<IDataBaseQuery> QUERIES = new ArrayList<>();

    static {
        QUERIES.add(new MySqlDataBaseQueryImpl());
    }

    public static IDataBaseQuery getStrategy(DataBaseTypeEnums dbType) {
        return QUERIES.stream().parallel().filter(item -> item.support(dbType)).findFirst().orElseThrow(() -> new GenerateException("找不到所选择的数据源处理Handler dbType:" + dbType + " 不匹配"));
    }

}
