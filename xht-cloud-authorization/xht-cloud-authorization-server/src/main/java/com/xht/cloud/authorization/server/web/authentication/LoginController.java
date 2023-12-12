package com.xht.cloud.authorization.server.web.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录接口，登录使用的接口
 *
 * @author vains
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {

        return "login";
    }


}
