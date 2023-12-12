package com.xht.cloud.framework.safety.repeat;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 ：自定义注解防止表单重复提交
 *
 * @author : 小糊涂
 **/
@Target(ElementType.METHOD) // 注解只能用于方法
@Retention(RetentionPolicy.RUNTIME) // 修饰注解的生命周期
@Documented
public @interface RepeatSubmit {

    /**
     * 防重复操作过期时间,默认5s
     */
    long expireTime() default 5;

    /**
     * 提示信息
     */
    String message() default "重复请求，请稍后重试!";
}
