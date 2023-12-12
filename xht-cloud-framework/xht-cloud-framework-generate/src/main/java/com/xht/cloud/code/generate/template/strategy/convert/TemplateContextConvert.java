package com.xht.cloud.code.generate.template.strategy.convert;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileConvert;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
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
 * 描述 ：convert 转换器
 *
 * @author : 小糊涂
 **/
public class TemplateContextConvert extends TemplateContext<GenFileConvert> {

    public TemplateContextConvert(final String packageName, final String path) {
        super("", "Convert", FileType.JAVA, "convert.vm", packageName, path);
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof GenFileConvert;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileConvert annotation, Field filedInfo) {
        super.setPackages("org.mapstruct.Mapper");
        super.setPackages("java.util.Objects");
        super.setPackages("java.util.List");
        super.setPackages("org.mapstruct.IterableMapping");
        super.setPackages("org.mapstruct.Named");
        super.setPackages("com.baomidou.mybatisplus.core.metadata.IPage");
        super.setPackages("com.xht.cloud.framework.core.api.response.PageResponse");
        super.setPackages("com.xht.cloud.framework.mybatis.tool.PageTool");
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.ADD.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.UPDATE.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.QUERY.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileResponse.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileDO.class, false)));
    }
}
