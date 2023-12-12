package com.xht.cloud.framework.security.aspect;

import cn.hutool.core.util.StrUtil;
import com.xht.cloud.framework.core.constant.RpcConstants;
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
    public void before(JoinPoint joinPoint, SkipAuthentication skipAuthentication) throws Exception {
        // 实际注入的inner实体由表达式后一个注解决定，即是方法上的@Inner注解实体，若方法上无@Inner注解，则获取类上的
        if (skipAuthentication == null) {
            Class<?> clazz = joinPoint.getTarget().getClass();
            skipAuthentication = AnnotationUtils.findAnnotation(clazz, SkipAuthentication.class);
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes attributes) {
            HttpServletRequest request = attributes.getRequest();
            String header = request.getHeader(RpcConstants.RPC_HEADER_KEY);
            if (Objects.nonNull(skipAuthentication) && skipAuthentication.value() && !StrUtil.equals(oauth2Properties.getRpcHeaderValue(), header)) {
                log.info("访问接口 {} 没有权限", joinPoint.getSignature().getName());
                throw new AccessDeniedException("暂无权限访问此接口!");
            }
        } else {
            throw new AccessDeniedException("未知请求，拒绝访问!");
        }
    }


}
