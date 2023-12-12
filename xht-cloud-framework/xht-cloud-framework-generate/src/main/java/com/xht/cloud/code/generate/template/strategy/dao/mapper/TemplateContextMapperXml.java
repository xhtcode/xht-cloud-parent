package com.xht.cloud.code.generate.template.strategy.dao.mapper;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
import com.xht.cloud.code.generate.annotation.file.GenFileMapperXml;
import com.xht.cloud.code.generate.constant.FileType;
import com.xht.cloud.code.generate.template.PackageFactory;
import com.xht.cloud.code.generate.template.TemplateContext;
import com.xht.cloud.code.generate.template.model.TemplateColumn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class TemplateContextMapperXml extends TemplateContext<GenFileMapperXml> {

    public TemplateContextMapperXml(String packageName, String path) {
        super("", "Mapper", FileType.XML, "mapper-xml.vm", packageName, path);
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof GenFileMapperXml;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileMapperXml annotation, Field filedInfo) {
        Class<?> convert = annotation.convert();
        String name = filedInfo.getType().getName();
        String pa = getClassName(convert) ? ClassUtil.getClassName(convert, false) : name;
        builder.javaTypeClass(pa);
        super.setPackages(pa);
        extension.put("DOpackageName", PackageFactory.getPath(ClassUtil.getClassName(GenFileDO.class, false)));
    }

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    @Override
    public int order() {
        return 3;
    }
}
