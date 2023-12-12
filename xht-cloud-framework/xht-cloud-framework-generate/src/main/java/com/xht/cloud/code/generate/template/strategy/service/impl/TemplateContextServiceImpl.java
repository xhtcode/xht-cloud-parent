package com.xht.cloud.code.generate.template.strategy.service.impl;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileConvert;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
import com.xht.cloud.code.generate.annotation.file.GenFileIService;
import com.xht.cloud.code.generate.annotation.file.GenFileMapper;
import com.xht.cloud.code.generate.annotation.file.GenFileRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileResponse;
import com.xht.cloud.code.generate.annotation.file.GenFileServiceImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileWrapper;
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
public class TemplateContextServiceImpl extends TemplateContext<GenFileServiceImpl> {


    public TemplateContextServiceImpl(final String packageName, final String path) {
        super("", "ServiceImpl", FileType.JAVA, "service-impl.vm", packageName, path);
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
        return annotation instanceof GenFileServiceImpl;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileServiceImpl annotation, Field filedInfo) {
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileConvert.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileMapper.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileIService.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.ADD.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.UPDATE.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.QUERY.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileResponse.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileWrapper.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileDO.class, false)));
        super.setPackages("lombok.RequiredArgsConstructor");
        super.setPackages("lombok.extern.slf4j.Slf4j");
        super.setPackages("java.util.Objects");
        super.setPackages("com.xht.cloud.framework.exception.business.BizException");
        super.setPackages("org.springframework.stereotype.Service");
        super.setPackages("com.xht.cloud.framework.core.api.response.PageResponse");
        super.setPackages("org.springframework.transaction.annotation.Transactional");
        super.setPackages("java.util.List");
        super.setPackages("com.baomidou.mybatisplus.core.metadata.IPage");
        super.setPackages("com.xht.cloud.framework.core.api.response.PageResponse");
        super.setPackages("com.xht.cloud.framework.mybatis.tool.PageTool");
    }

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    @Override
    public int order() {
        return 5;
    }
}
