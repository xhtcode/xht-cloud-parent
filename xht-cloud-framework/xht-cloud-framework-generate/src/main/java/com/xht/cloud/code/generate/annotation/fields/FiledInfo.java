package com.xht.cloud.code.generate.annotation.fields;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 ：字段信息
 *
 * @author : 小糊涂
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FiledInfo {

    /**
     * @return 数据库字段值
     */
    String value();


    /**
     * @return 字段描述
     */
    String describe() default "";


    /**
     * @return 默认值
     */
    String columnDefault() default "";

    /**
     * 字段长度
     */
    int length() default 0;


    /**
     * @return 是否为空 true为空
     */
    boolean isNullable() default false;

    String dbType() default "";
}
