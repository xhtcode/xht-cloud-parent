package com.xht.cloud.framework.safety.repeat.aop;

import cn.hutool.crypto.digest.MD5;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.safety.repeat.annotation.RepeatSubmitLimit;
import com.xht.cloud.framework.safety.repeat.dao.RepeatSubmitDao;
import com.xht.cloud.framework.safety.repeat.exception.RepeatSubmitException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RepeatSubmitAspect {

    private final RepeatSubmitDao repeatSubmitDao;

    @Before("@annotation(submitLimit)")
    public void before(JoinPoint joinPoint, RepeatSubmitLimit submitLimit) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttributes).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String key = getKey(request, joinPoint.getArgs(), signature.getMethod());
        boolean success = repeatSubmitDao.limitLock(key, submitLimit.expireTime(), submitLimit.expireTimeType());
        // 锁定失败，抛出异常
        if (!success) {
            log.info("[beforePointCut][方法({}) key({}) 存在重复请求]", joinPoint.getSignature().toString(), key);
            throw new RepeatSubmitException(submitLimit.message());
        }
    }

    private String getKey(HttpServletRequest request, Object[] args, Method method) {
        String resultKey = method.getName() + request.getRequestURI() +
                StringUtils.arrayToDelimitedString(args, "");
        return MD5.create().digestHex(resultKey);
    }
}
