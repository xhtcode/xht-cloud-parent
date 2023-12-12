package com.xht.cloud.authorization.server.web.authentication;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@RequestMapping("/oauth2")
@RestController
public class UserController {

    @GetMapping("/userinfo")
    public R<CustomUserDetails> userInfo() {
        Authentication authentication = SecurityContextUtil.getAuthentication().orElse(null);
        if (Objects.isNull(authentication)){
            return R.restResult(401,"暂未登录",null);
        }
        Object principal = authentication.getPrincipal();
        return R.ok((CustomUserDetails) principal);
    }
}
