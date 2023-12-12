package com.xht.cloud.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.gateway.service.ValidateCodeService;
import com.xht.cloud.gateway.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * 描述 ：验证码过滤器
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateCodeGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final static String[] VALIDATE_URL = new String[]{"/oauth2/token", "/auth/register"};

    private final ValidateCodeService validateCodeService;


    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // 非登录/注册请求或验证码关闭，不处理
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), VALIDATE_URL)) {
               return chain.filter(exchange);
            }
            try {
                validateCodeService.checkCaptcha(request.getQueryParams().getFirst("code"), request.getQueryParams().getFirst("uuid"));
            } catch (Exception e) {
                e.printStackTrace();
                return WebUtils.webFluxResponseWriter(exchange.getResponse(), R.failed(e.getMessage()));
            }
            return chain.filter(exchange);
        };
    }


}
