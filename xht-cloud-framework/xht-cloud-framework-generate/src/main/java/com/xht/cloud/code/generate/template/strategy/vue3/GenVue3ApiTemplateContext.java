package com.xht.cloud.code.generate.template.strategy.vue3;

import com.xht.cloud.code.generate.annotation.file.GenVue3Api;
import com.xht.cloud.code.generate.config.GenCodeInfo;
import com.xht.cloud.code.generate.constant.FileType;
import com.xht.cloud.code.generate.template.PackageFactory;
import com.xht.cloud.code.generate.template.TemplateContext;
import com.xht.cloud.code.generate.template.model.TemplateColumn;
import com.xht.cloud.code.generate.util.GeneratorUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/

public class GenVue3ApiTemplateContext extends TemplateContext<GenVue3Api> {

    public GenVue3ApiTemplateContext(final String packageName, final String path) {
        super("", "index", FileType.TS, "vue3/api.vm", packageName, path);
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
        return annotation instanceof GenVue3Api;
    }

    /**
     * 注解执行返回信息
     *
     * @param builder    构建
     * @param annotation 注解
     * @param filedInfo  字段信息注解
     */
    @Override
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenVue3Api annotation, Field filedInfo) {
        setFileNameOnly(true);
        super.extension.put("typesPackage", PackageFactory.getPath("typesPackage"));
    }

    /**
     * @param application
     */
    @Override
    public void execute(GenCodeInfo application) {
        GeneratorUtils.execute(application, getPageName(), "", "apiPackage");
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
