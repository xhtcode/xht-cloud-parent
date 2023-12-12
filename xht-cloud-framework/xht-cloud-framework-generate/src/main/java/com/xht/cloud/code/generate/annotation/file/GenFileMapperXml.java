package com.xht.cloud.code.generate.annotation.file;

import com.xht.cloud.code.generate.template.model.None;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 ：mybatis dao 映射文件
 *
 * @author : 小糊涂
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface GenFileMapperXml {


    /**
     * 字段需要转换的类型
     */
    Class<?> convert() default None.class;
}
