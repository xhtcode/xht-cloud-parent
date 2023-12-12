package com.xht.cloud.gateway.handler;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.gateway.exception.CaptchaException;
import com.xht.cloud.gateway.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * 描述 ：验证码 获取
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {

    private final ValidateCodeService validateCodeService;

    @Override
    public @NonNull Mono<ServerResponse> handle(ServerRequest request) {
        R<?> result;
        try {
            String uuid = request.queryParam("uuid").orElse(null);
            if (StringUtils.hasText(uuid)) {
                //  Boolean aBoolean = redisService.delete(GlobalRedisEnums.LOGIN_CAPTCHA_KEY.buildKey(uuid));
                log.info("删除验证码结果={}uuid={}", true, uuid);
            }
            result = validateCodeService.createCaptcha();
        } catch (CaptchaException | IOException e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(result));
    }
}
