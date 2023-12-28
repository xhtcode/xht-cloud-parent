package com.xht.cloud.framework.core.api.request;

import io.swagger.v3.oas.annotations.Hidden;

import java.io.Serializable;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Hidden
public abstract class UpdateRequest<T> extends Request implements Serializable {

    /**
     * 获取主键
     */
    public abstract String getPkId();

}
