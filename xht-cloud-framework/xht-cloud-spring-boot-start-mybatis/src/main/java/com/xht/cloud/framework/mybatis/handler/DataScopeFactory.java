package com.xht.cloud.framework.mybatis.handler;

import com.xht.cloud.framework.mybatis.core.enums.DataScopeTypeEnums;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class DataScopeFactory {

    private final Map<DataScopeTypeEnums, DataScopeHandler> handler;

    public DataScopeFactory() {
        handler = new HashMap<>();
    }

    public void put(DataScopeTypeEnums key, DataScopeHandler value) {
        handler.put(key, value);
    }

    public DataScopeHandler getDataScopeHandler(DataScopeTypeEnums typeEnums) {
        return handler.get(typeEnums);
    }

}
