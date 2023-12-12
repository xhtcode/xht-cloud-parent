package com.xht.cloud.code.generate.template.strategy.controller.request;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileRequest;
import com.xht.cloud.code.generate.constant.FileType;
import com.xht.cloud.code.generate.constant.RequestConstant;
import com.xht.cloud.code.generate.template.PackageFactory;
import com.xht.cloud.code.generate.template.TemplateContext;
import com.xht.cloud.code.generate.template.model.TemplateColumn;
import lombok.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class TemplateContextRequest extends TemplateContext<GenFileRequest> {

    private final RequestConstant requestConstant;

    public TemplateContextRequest(final String packageName, final String path, @NonNull final RequestConstant requestConstant) {
        super("", String.format("%sRequest", requestConstant.getName()), FileType.JAVA, "request.vm", packageName, path);
        super.addIgnoreField("delFlag");
        super.addIgnoreField("createBy");
        super.addIgnoreField("updateBy");
        super.addIgnoreField("createTime");
        super.addIgnoreField("updateTime");
        this.requestConstant = requestConstant;
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof GenFileRequest;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileRequest annotation, Field filedInfo) {
        super.setPackages("lombok.Data");
        super.setPackages("io.swagger.v3.oas.annotations.media.Schema");
        extension.put("symbolDesc", requestConstant.getDesc());
        extension.put("symbol", requestConstant.getName());
        extension.put("request", "");
        extension.put("filedInfo", "1");
        Class<?> convert = annotation.convert();
        String name = filedInfo.getType().getName();
        String pa = getClassName(convert) ? ClassUtil.getClassName(convert, false) : name;
        TemplateColumn build = builder.build();
        boolean nullable = build.isNullable();
        boolean a = (nullable && requestConstant ==  RequestConstant.UPDATE && Objects.equals("1", build.getPk()));
        super.setPackages("com.xht.cloud.framework.web.validation.group.Create");
        super.setPackages("com.xht.cloud.framework.web.validation.group.Update");
        if ((nullable && requestConstant == RequestConstant.BASE) || a) {
            if (StrUtil.contains(pa, "String")) {
                super.setPackages("jakarta.validation.constraints.NotBlank");
                builder.redundancy("2");
            } else if (StrUtil.contains(pa, "Integer")) {
                super.setPackages("com.xht.cloud.framework.web.validation.IntegerInterval");
                builder.redundancy("1");
            } else {
                builder.redundancy("3");
                super.setPackages("jakarta.validation.constraints.NotNull");
            }
        }
        if (requestConstant == RequestConstant.UPDATE) {
            builder.javaTypeClass(pa);
            super.setPackages(pa);
            extension.put("filedInfo", "0");
        }
        if (requestConstant == RequestConstant.BASE) {
            builder.javaTypeClass(pa);
            super.setPackages(pa);
            extension.put("filedInfo", "0");
            extension.put("request", "extends Request ");
            super.setPackages("com.xht.cloud.framework.core.api.request.Request");
        } else {
            String path = PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.BASE.getName());
            extension.put("request", String.format("extends %s ", path.substring(path.lastIndexOf(".") + 1)));
        }
    }

    /**
     * 执行后判断
     *
     * @param templateColumn 已经封装好的字段信息
     * @return {@link Boolean}
     */
    @Override
    public boolean executeAfter(TemplateColumn templateColumn) {
        switch (requestConstant) {
            case BASE -> {
                return Objects.equals("1", templateColumn.getPk());
            }
            case UPDATE -> {
                return Objects.equals("0", templateColumn.getPk());
            }
        }
        return false;
    }

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    @Override
    public int order() {
        return requestConstant == RequestConstant.BASE ? 1 : 2;
    }

    /**
     * 生成当前类的序列值，可以不设置 默认空字符串
     */
    @Override
    public String getKey() {
        return requestConstant.getName();
    }
}
