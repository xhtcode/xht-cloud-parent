package com.xht.cloud.code.generate.template.strategy.vue3;

import com.xht.cloud.code.generate.annotation.file.GenVue3Index;
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

public class GenVue3IndexTemplateContext extends TemplateContext<GenVue3Index> {


    public GenVue3IndexTemplateContext(final String packageName, final String path) {
        super("", "index", FileType.VUE, "vue3/index.vm", packageName, path);
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
        return annotation instanceof GenVue3Index;
    }

    /**
     * 注解执行返回信息
     *
     * @param builder    构建
     * @param annotation 注解
     * @param filedInfo  字段信息注解
     */
    @Override
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenVue3Index annotation, Field filedInfo) {
        setFileNameOnly(true);
        super.extension.put("typesPackage", PackageFactory.getPath("typesPackage"));
        super.extension.put("apiPackage", PackageFactory.getPath("apiPackage"));
        super.extension.put("addOrUpdatePackage", PackageFactory.getPath("addOrUpdatePackage"));
    }

    /**
     * @param application
     */
    @Override
    public void execute(GenCodeInfo application) {
        GeneratorUtils.execute(application, getPageName(), "index", "indexPackage");
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
