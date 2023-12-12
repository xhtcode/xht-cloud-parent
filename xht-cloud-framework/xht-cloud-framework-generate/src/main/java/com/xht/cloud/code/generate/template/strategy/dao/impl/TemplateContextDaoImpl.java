package com.xht.cloud.code.generate.template.strategy.dao.impl;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileDao;
import com.xht.cloud.code.generate.annotation.file.GenFileDaoImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
import com.xht.cloud.code.generate.annotation.file.GenFileMapper;
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
public class TemplateContextDaoImpl extends TemplateContext<GenFileDaoImpl> {


    public TemplateContextDaoImpl(String packageName, String path) {
        super("", "DaoImpl",  FileType.JAVA,"dao-impl.vm", packageName, path);
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof GenFileDaoImpl;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileDaoImpl annotation, Field filedInfo) {
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileDO.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileMapper.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileDao.class, false)));
        super.setPackages("lombok.extern.slf4j.Slf4j");
        super.setPackages("org.springframework.stereotype.Repository");
        super.setPackages("lombok.RequiredArgsConstructor");
        super.setPackages("com.xht.cloud.framework.mybatis.mapper.BaseMapperX");
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
