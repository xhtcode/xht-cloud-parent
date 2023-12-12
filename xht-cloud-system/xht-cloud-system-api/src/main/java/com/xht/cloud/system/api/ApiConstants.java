package com.xht.cloud.system.api;

import com.xht.cloud.framework.core.constant.RpcConstants;

/**
 * 描述 ：API 相关的常量
 *
 * @author : 小糊涂
 **/
public final class ApiConstants {

    private ApiConstants() {
    }

    /**
     * 服务名
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public final static String SERVER_NAME = "xht-cloud-system";

    public final static String PREFIX = RpcConstants.RPC_API_PREFIX + "/sys";


}
