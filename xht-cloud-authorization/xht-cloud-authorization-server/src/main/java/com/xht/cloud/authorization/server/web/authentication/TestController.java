package com.xht.cloud.authorization.server.web.authentication;

import com.xht.cloud.authorization.server.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author vains
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final AuthorizationService oAuth2AuthorizationService;

    @GetMapping("/test01/{token}")
    public Object test01(@PathVariable("token") String token) {
        return oAuth2AuthorizationService.findByAccessTokenValue(token);
    }
}
