package com.xht.cloud.framework.security.web.authentication;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.security.support.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 描述 ：令牌吊销成功后处理器
 *
 * @author : 小糊涂
 **/
@Slf4j
public class TokenRevocationAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getParameter(OAuth2ParameterNames.TOKEN);
        WebUtil.renderString(response, R.ok("令牌吊销成功！", token));
        log.info("令牌 吊销成功: access_token={}", token);
    }
}
