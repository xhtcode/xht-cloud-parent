package com.xht.cloud.code.generate.template.strategy.api;

import com.xht.cloud.code.generate.annotation.file.GenApiImpl;
import com.xht.cloud.code.generate.constant.FileType;
import com.xht.cloud.code.generate.template.TemplateContext;
import com.xht.cloud.code.generate.template.model.TemplateColumn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class TemplateContextApi  extends TemplateContext<GenApiImpl>{


    public TemplateContextApi(String pathPrefix, String pathSuffix, FileType fileType, String templatePath, String packageName, String path) {
        super(pathPrefix, pathSuffix, fileType, templatePath, packageName, path);
    }

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    public int order() {
        return 6;
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return false;
    }

    /**
     * 注解执行返回信息
     * <p>
     * 可以根据当前注解拿到的信息 添加一些模板变量
     *
     * @param builder    构建
     * @param annotation 注解
     * @param filedInfo  字段信息注解
     */
    @Override
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenApiImpl annotation, Field filedInfo) {

    }
}
