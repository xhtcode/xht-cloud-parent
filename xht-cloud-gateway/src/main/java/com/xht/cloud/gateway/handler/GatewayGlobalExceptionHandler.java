package com.xht.cloud.gateway.handler;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.exception.enums.GlobalErrorStatusCode;
import com.xht.cloud.gateway.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 描述 ：网关统一异常处理
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class GatewayGlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public @NonNull Mono<Void> handle(ServerWebExchange exchange, @NonNull Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        R<String> result;
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        if (ex instanceof NotFoundException) {
            //服务未找到或服务不可用
            result = R.failed(GlobalErrorStatusCode.SERVICE_UNAVAILABLE);
        } else if (ex instanceof ResponseStatusException exception) {
            //HTTP响应状态码相关的异常的基类。
            result = R.restResult(exception.getStatusCode().value(), ex.getMessage(), null);
        } else {
            //内部服务器错误
            result = R.failed(GlobalErrorStatusCode.UNKNOWN);
        }
        log.error("【网关异常处理】请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());
        return WebUtils.webFluxResponseWriter(response, result);
    }
}
