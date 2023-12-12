package com.xht.cloud.code.generate.template.strategy.controller;

import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.file.GenFileController;
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
public class TemplateContextController extends TemplateContext<GenFileController> {
    public TemplateContextController(final String packageName, final String path) {
        super("", "Controller", FileType.JAVA, "controller.vm", packageName, path);
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
        return annotation instanceof GenFileController;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenFileController annotation, Field filedInfo) {
        super.setPackages("lombok.RequiredArgsConstructor");
        super.setPackages("com.xht.cloud.framework.safety.repeat.RepeatSubmit");
        super.setPackages("java.util.List");
        super.setPackages("com.xht.cloud.framework.web.validation.group.Update");
        super.setPackages("com.xht.cloud.framework.web.validation.group.Create");
        super.setPackages("org.springframework.validation.annotation.Validated");
        super.setPackages("lombok.extern.slf4j.Slf4j");
        super.setPackages("com.xht.cloud.framework.core.api.R");
        super.setPackages("static com.xht.cloud.framework.core.api.R.ok");
        super.setPackages("com.xht.cloud.framework.core.api.response.PageResponse");
        super.setPackages("org.springframework.web.bind.annotation.DeleteMapping");
        super.setPackages("org.springframework.web.bind.annotation.GetMapping");
        super.setPackages("org.springframework.web.bind.annotation.PathVariable");
        super.setPackages("org.springframework.web.bind.annotation.PostMapping");
        super.setPackages("org.springframework.web.bind.annotation.PutMapping");
        super.setPackages("org.springframework.web.bind.annotation.RequestBody");
        super.setPackages("org.springframework.web.bind.annotation.RequestMapping");
        super.setPackages("org.springframework.web.bind.annotation.RestController");
        super.setPackages("org.springframework.web.bind.annotation.RequestBody");
        super.setPackages("io.swagger.v3.oas.annotations.tags.Tag");
        super.setPackages("io.swagger.v3.oas.annotations.Operation");
        super.setPackages("io.swagger.v3.oas.annotations.Operation");
        super.setPackages("io.swagger.v3.oas.annotations.Parameter");
        super.setPackages("org.springframework.security.access.prepost.PreAuthorize");
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.ADD.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.UPDATE.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileRequest.class, false) + RequestConstant.QUERY.getName()));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileResponse.class, false)));
        super.setPackages(PackageFactory.getPath(ClassUtil.getClassName(GenFileIService.class, false)));
    }
}
