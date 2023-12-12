package com.xht.cloud.framework.security.web.access;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.security.support.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import java.io.IOException;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
public class ResourceAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("【资源服务器】AccessDeniedHandler `{}` {}", request.getRequestURI(), accessDeniedException.getMessage(), accessDeniedException);
        R<String> result = R.restResult(401, "权限不足无法访问!", accessDeniedException.getMessage());
        if (accessDeniedException instanceof AuthorizationServiceException) {
            result.setMsg("由于系统问题而无法处理权限时抛出");
        } else if (accessDeniedException instanceof MissingCsrfTokenException) {
            result.setMsg("Csrf令牌缺失");
        } else if (accessDeniedException instanceof InvalidCsrfTokenException) {
            result.setMsg("Csrf令牌无效");
        } else if (accessDeniedException instanceof CsrfException) {
            result.setMsg("Csrf令牌");
        } else if (accessDeniedException instanceof org.springframework.security.web.server.csrf.CsrfException) {
            result.setMsg("Csrf令牌");
        }
        WebUtil.renderString(response, result);
    }


}
