package com.xht.cloud.code.generate.template;

import com.xht.cloud.code.generate.config.GenCodeInfo;
import com.xht.cloud.code.generate.template.model.TemplateColumn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public interface ITemplateContext<T extends Annotation> {

    /**
     * 注解执行返回信息
     * <p>
     * 可以根据当前注解拿到的信息 添加一些模板变量
     *
     * @param builder    构建
     * @param annotation 注解
     * @param filedInfo  字段信息注解
     */
    public void execute(TemplateColumn.TemplateColumnBuilder builder, T annotation, Field filedInfo);


    default void execute(GenCodeInfo application) {

    }

}
