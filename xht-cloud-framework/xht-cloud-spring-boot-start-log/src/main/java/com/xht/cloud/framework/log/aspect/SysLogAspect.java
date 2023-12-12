package com.xht.cloud.framework.log.aspect;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.xht.cloud.framework.log.annotation.SysLog;
import com.xht.cloud.framework.log.dto.SysLogDTO;
import com.xht.cloud.framework.log.event.SysLogEvent;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import com.xht.cloud.framework.web.ip.IPUtils;
import com.xht.cloud.framework.web.servlet.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.util.Objects;

/**
 * 描述 ：系统日志切面
 *
 * @author : 小糊涂
 **/
@Slf4j
@Aspect
public class SysLogAspect {

    @Value("${spring.application.name}")
    private String applicationName;


    /**
     * 定义切面需要使用的注释
     */
    @Pointcut("@annotation(com.xht.cloud.framework.log.annotation.SysLog)")
    public void dataSignaturePoint() {
    }

    /**
     * 数据审计日志切点
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @param sysLog    {@link SysLog}
     * @return Object
     */
    @Around("dataSignaturePoint()&&@annotation(sysLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, SysLog sysLog) throws Throwable {
        SysLogDTO sysLogDTO = new SysLogDTO();
        Object result = null;
        try {
            result = joinPoint.proceed();
            HttpServletRequest request = ServletUtil.getRequest();
            sysLogDTO.setServerName(applicationName);
            sysLogDTO.setBusName(sysLog.busName());
            sysLogDTO.setBusDesc(sysLog.description());
            if (Objects.nonNull(request)) {
                sysLogDTO.setOperateMethod(request.getMethod());
                sysLogDTO.setRequestUrl(request.getRequestURI());
                sysLogDTO.setOperateIp(IPUtils.getRealIP(request));
            }
            sysLogDTO.setOperateType(0);
            sysLogDTO.setOperateName(SecurityContextUtil.getUserName());
            sysLogDTO.setOperateLocation(InetAddress.getLocalHost().getHostAddress());
            sysLogDTO.setOperateParam(JSON.toJSONString(""));
            sysLogDTO.setResult(JSON.toJSONString(result));
        } catch (Exception e) {
            sysLogDTO.setStatus(1);
            sysLogDTO.setErrorMsg(e.getMessage());
            log.info("【系统日志切面】异常 e = {}", e.getMessage(), e);
        } finally {
            SpringUtil.publishEvent(new SysLogEvent(sysLogDTO));
        }
        return result;
    }

}
