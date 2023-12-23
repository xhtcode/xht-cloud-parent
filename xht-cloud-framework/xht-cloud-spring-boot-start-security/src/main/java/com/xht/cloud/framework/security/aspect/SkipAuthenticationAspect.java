package com.xht.cloud.framework.security.aspect;

import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.security.Oauth2Properties;
import com.xht.cloud.framework.security.annotaion.SkipAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * 描述 ：校验外放请求是否合法
 *
 * @author : 小糊涂
 **/
@Slf4j
@Aspect
@RequiredArgsConstructor
public class SkipAuthenticationAspect {


    private final Oauth2Properties oauth2Properties;

    @Before("@within(skipAuthentication) || @annotation(skipAuthentication)")
    public void before(JoinPoint joinPoint, SkipAuthentication skipAuthentication) {
        if (skipAuthentication == null) {
            Class<?> clazz = joinPoint.getTarget().getClass();
            skipAuthentication = AnnotationUtils.findAnnotation(clazz, SkipAuthentication.class);
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes attributes) {
            HttpServletRequest request = attributes.getRequest();
            assert skipAuthentication != null;
            if (skipAuthentication.value() && !Objects.equals(StringUtils.emptyDefault(skipAuthentication.headerValue(), oauth2Properties.getRpcHeaderValue()), request.getHeader(skipAuthentication.headerKey()))) {
                log.error("访问接口 {} 没有权限", joinPoint.getSignature().getName());
                throw new AccessDeniedException("暂无权限访问此接口!");
            }
        } else {
            throw new AccessDeniedException("未知请求，拒绝访问!");
        }
    }


}
