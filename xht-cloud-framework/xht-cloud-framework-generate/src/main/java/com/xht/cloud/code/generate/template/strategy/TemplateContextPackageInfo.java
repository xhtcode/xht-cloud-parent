package com.xht.cloud.code.generate.template.strategy;

import com.xht.cloud.code.generate.annotation.file.GenPackageInfo;
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
public class TemplateContextPackageInfo extends TemplateContext<GenPackageInfo> {

    public TemplateContextPackageInfo(final String packageName, final String path) {
        super("", "package-info", FileType.JAVA, "package-info.vm", packageName, path);
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof GenPackageInfo;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenPackageInfo annotation, Field filedInfo) {
        setFileNameOnly(true);
    }
}
