package com.xht.cloud.code.generate.template.strategy.controller.response;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileResponse;
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
public class TemplateContextResponse extends TemplateContext<GenFileResponse> {

    public TemplateContextResponse(final String packageName, final String path) {
        super("", "Response", FileType.JAVA, "response.vm", packageName, path);
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
        return annotation instanceof GenFileResponse;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileResponse annotation, Field filedInfo) {
        Class<?> convert = annotation.convert();
        String name = filedInfo.getType().getName();
        String pa = getClassName(convert) ? ClassUtil.getClassName(convert, false) : name;
        builder.javaTypeClass(pa);
        super.setPackages(pa);
        super.setPackages("lombok.Data");
        super.setPackages("io.swagger.v3.oas.annotations.media.Schema");
        super.setPackages("com.xht.cloud.framework.core.api.response.Response");
        extension.put("response", "extends Response ");
    }


    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    @Override
    public int order() {
        return 1;
    }
}
