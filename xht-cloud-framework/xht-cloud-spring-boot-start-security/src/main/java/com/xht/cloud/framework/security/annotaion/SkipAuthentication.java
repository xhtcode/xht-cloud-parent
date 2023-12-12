package com.xht.cloud.framework.security.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
     * 是否AOP统一处理
     * @return false, true
     */
    boolean value() default true;


    /**
     * 需要特殊判空的字段(预留)
     * @return {}
     */
    String[] field() default {};


}
