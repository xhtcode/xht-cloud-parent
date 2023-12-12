package com.xht.cloud.framework.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@RefreshScope
public class Oauth2Properties {

    @Value("${xht.oauth2.clientServerUrl}")
    private String clientServerUrl;

    @Value("${xht.oauth2.opaquetoken.introspectionUri}")
    private String introspectionUri;

    @Value("${xht.oauth2.opaquetoken.clientId}")
    private String clientId;

    @Value("${xht.oauth2.opaquetoken.clientSecret}")
    private String clientSecret;

    @Value("${xht.feign.header.auth:123456}")
    private String rpcHeaderValue;
}
