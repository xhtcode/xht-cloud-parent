package com.xht.cloud.code.generate.template.strategy.dao.wrapper;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
import com.xht.cloud.code.generate.annotation.file.GenFileWrapper;
import com.xht.cloud.code.generate.constant.FileType;
import com.xht.cloud.code.generate.template.PackageFactory;
import com.xht.cloud.code.generate.template.TemplateContext;
import com.xht.cloud.code.generate.template.model.TemplateColumn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class TemplateContextWrapper extends TemplateContext<GenFileWrapper> {

    public TemplateContextWrapper(String packageName, String path) {
        super("", "Wrapper", FileType.JAVA, "wrapper.vm", packageName, path);
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
        return annotation instanceof GenFileWrapper;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileWrapper annotation, Field filedInfo) {
        String name = filedInfo.getType().getName();
        if (!Objects.equals("java.lang.String", name)) {
            super.setPackages("org.springframework.util.ObjectUtils");
            builder.convertMethod("!ObjectUtils.isEmpty");
        } else {
            super.setPackages("com.xht.cloud.framework.core.support.StringUtils");
            builder.convertMethod("StringUtils.hasText");
        }
        super.setPackages("com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper");
        super.setPackages("com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper");
        super.setPackages("com.xht.cloud.framework.mybatis.wrapper.EntityWrapper");
        super.setPackages("java.util.Objects");
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileDO.class, false)));
    }

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    @Override
    public int order() {
        return 2;
    }
}
