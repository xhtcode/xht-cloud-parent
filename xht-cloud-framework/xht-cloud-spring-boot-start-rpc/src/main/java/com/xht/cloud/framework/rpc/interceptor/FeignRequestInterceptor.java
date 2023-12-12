package com.xht.cloud.framework.rpc.interceptor;

import com.xht.cloud.framework.core.constant.RpcConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 描述 ：feign 拦截器
 *
 * @author : 小糊涂
 **/
@Slf4j
@RefreshScope
public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${xht.feign.header.auth:123456}")
    private String rpcHeaderValue;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //为所有的feign请求加上一个header
        requestTemplate.header(RpcConstants.RPC_HEADER_KEY, rpcHeaderValue);
    }
}
