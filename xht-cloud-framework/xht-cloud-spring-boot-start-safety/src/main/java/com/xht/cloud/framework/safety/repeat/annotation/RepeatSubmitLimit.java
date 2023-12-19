package com.xht.cloud.framework.safety.repeat.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 描述 ：自定义注解防止表单重复提交
 *
 * @author : 小糊涂
 **/
@Target(ElementType.METHOD) // 注解只能用于方法
@Retention(RetentionPolicy.RUNTIME) // 修饰注解的生命周期
@Documented
public @interface RepeatSubmitLimit {

    /**
     * 防重复操作过期时间,默认5s
     */
    long expireTime() default 5;


    /**
     * 时间单位，默认为 SECONDS 秒
     */
    TimeUnit expireTimeType() default TimeUnit.SECONDS;


    /**
     * 提示信息
     */
    String message() default "重复请求，请稍后重试!";

}
