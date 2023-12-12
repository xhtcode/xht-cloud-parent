package com.xht.cloud.framework.security.web.authentication;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.security.oauth2.core.http.converter.OAuth2ErrorParametersConverter;
import com.xht.cloud.framework.security.support.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.Map;

/**
 * 描述 ：token生成成功 响应信息处理
 * token端点
 *
 * @author : 小糊涂
 * @see org.springframework.security.oauth2.server.authorization.web.OAuth2TokenEndpointFilter
 **/
@Slf4j
public class TokenEndpointAuthenticationFailureHandler implements AuthenticationFailureHandler {

    protected final Converter<OAuth2Error, Map<String, String>> errorParametersConverter;

    public TokenEndpointAuthenticationFailureHandler() {
        this.errorParametersConverter = new OAuth2ErrorParametersConverter();
        log.info("token 端点 当身份验证尝试失败时调用");
    }

    /**
     * 当身份验证尝试失败时调用 token端点.
     *
     * @param request   发生身份验证尝试的请求。
     * @param response  响应。
     * @param exception 为拒绝身份验证而抛出的异常请求
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        OAuth2Error oauth2Error = ((OAuth2AuthenticationException) exception).getError();
        Map<String, String> errorParameters = this.errorParametersConverter.convert(oauth2Error);
        WebUtil.renderString(response, HttpStatus.BAD_REQUEST, R.restResult(401, oauth2Error.getDescription(), errorParameters));
        log.error("token endpoint 异常 error={}", errorParameters, exception);
    }


}
