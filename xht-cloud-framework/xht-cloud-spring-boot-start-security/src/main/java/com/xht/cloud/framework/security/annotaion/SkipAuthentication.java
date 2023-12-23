package com.xht.cloud.framework.security.annotaion;

import com.xht.cloud.framework.core.constant.RpcConstants;

import java.lang.annotation.*;

/**
 * 描述 ：跳过权限认证
 *
 * @author : 小糊涂
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipAuthentication {

    /**
     * 是否区分内部调用
     * @return {@code false} 不区分, {@code true} 如果内部调用则放行不是内部调用则拦截
     */
    boolean value() default true;

    String headerKey() default RpcConstants.RPC_HEADER_KEY;

    String headerValue() default "";
}
