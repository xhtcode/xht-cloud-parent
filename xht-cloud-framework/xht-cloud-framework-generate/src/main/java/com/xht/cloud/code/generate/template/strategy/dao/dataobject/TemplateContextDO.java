package com.xht.cloud.code.generate.template.strategy.dao.dataobject;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
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
public class TemplateContextDO extends TemplateContext<GenFileDO> {


    public TemplateContextDO(final String packageName, final String path) {
        super("", "DO", FileType.JAVA, "DO.vm", packageName, path);
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
        return annotation instanceof GenFileDO;
    }

    /**
     * 注解执行返回信息
     *
     * @param builder    构建
     * @param annotation 注解
     * @param filedInfo  字段信息注解
     */
    @Override
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileDO annotation, Field filedInfo) {
        Class<?> convert = annotation.convert();
        String name = filedInfo.getType().getName();
        String pa = getClassName(convert) ? ClassUtil.getClassName(convert, false) : name;
        builder.javaTypeClass(pa);
        super.setPackages(pa);
        super.setPackages("com.baomidou.mybatisplus.annotation.IdType");
        super.setPackages("com.baomidou.mybatisplus.annotation.TableField");
        super.setPackages("com.baomidou.mybatisplus.annotation.TableId");
        super.setPackages("com.baomidou.mybatisplus.annotation.TableName");
        super.setPackages("com.xht.cloud.framework.mybatis.core.dataobject.BaseDO");
        super.setPackages("lombok.Data");
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