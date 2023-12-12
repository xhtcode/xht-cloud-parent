package com.xht.cloud.framework.security.web.authentication;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.security.support.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述 ：令牌吊销失败时执行
 *
 * @author : 小糊涂
 **/
@Slf4j
public class TokenRevocationAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     *                  request.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String token = request.getParameter(OAuth2ParameterNames.TOKEN);
        Map<String, String> errorData = new HashMap<>();
        errorData.put(OAuth2ParameterNames.TOKEN, token);
        errorData.put(OAuth2ParameterNames.ERROR, exception.getMessage());
        WebUtil.renderString(response, HttpStatus.BAD_REQUEST, R.restResult(401, "令牌吊销失败", errorData));
        log.error("token endpoint 异常 error={}", token, exception);
    }
}
