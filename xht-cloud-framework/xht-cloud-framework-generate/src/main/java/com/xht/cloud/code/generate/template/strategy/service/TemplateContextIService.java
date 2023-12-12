package com.xht.cloud.code.generate.template.strategy.service;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileIService;
import com.xht.cloud.code.generate.annotation.file.GenFileRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileResponse;
import com.xht.cloud.code.generate.constant.FileType;
import com.xht.cloud.code.generate.constant.RequestConstant;
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
public class TemplateContextIService extends TemplateContext<GenFileIService> {


    public TemplateContextIService(final String packageName, final String path) {
        super("I", "Service", FileType.JAVA, "i-service.vm", packageName, path);
        super.addIgnoreField("delFlag");
        super.addIgnoreField("createBy");
        super.addIgnoreField("updateBy");
        super.addIgnoreField("createTime");
        super.addIgnoreField("updateTime");
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof GenFileIService;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileIService annotation, Field filedInfo) {
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.ADD.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.UPDATE.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.QUERY.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileResponse.class, false)));
        super.setPackages("com.xht.cloud.framework.core.api.response.PageResponse");
        super.setPackages("java.util.List");
    }

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    @Override
    public int order() {
        return 4;
    }
}
