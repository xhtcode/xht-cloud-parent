package com.xht.cloud.code.generate.annotation.file;

import com.xht.cloud.code.generate.template.model.None;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 ：基础接收参数 接口接收参数
 *
 * @author : 小糊涂
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface GenFileRequest {

    /**
     * 字段需要转换的类型
     */
    Class<?> convert() default None.class;
}
