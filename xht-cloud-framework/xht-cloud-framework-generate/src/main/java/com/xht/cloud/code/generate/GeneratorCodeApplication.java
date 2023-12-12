package com.xht.cloud.code.generate;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.xht.cloud.code.generate.annotation.GenInfoAnnotation;
import com.xht.cloud.code.generate.annotation.Module;
import com.xht.cloud.code.generate.config.ConfigInfo;
import com.xht.cloud.code.generate.config.GenCodeInfo;
import com.xht.cloud.code.generate.exception.GenerateException;
import com.xht.cloud.code.generate.template.TemplateContext;
import com.xht.cloud.code.generate.template.TemplateContextPool;
import com.xht.cloud.code.generate.template.model.TemplateInfo;
import com.xht.cloud.code.generate.util.GeneratorUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 ：代码生成器 === 链接数据库生成代码
 *
 * @author : 小糊涂
 **/
@Slf4j
public class GeneratorCodeApplication {

    private static final GenCodeInfo application = new GenCodeInfo();


    private GeneratorCodeApplication(ConfigInfo configInfo) {
        if (Objects.isNull(configInfo) || StrUtil.isBlank(configInfo.getAuthor())) {
            throw new GenerateException("configInfo is must not empty! ");
        }
        application.setConfigInfo(configInfo);
    }

    public static GeneratorCodeApplication config(ConfigInfo configInfo) {
        return new GeneratorCodeApplication(configInfo);
    }

    public GeneratorCodeApplication register(TemplateContext<? extends Annotation> context) {
        TemplateContextPool.register(context);
        return this;
    }

    public GeneratorCodeApplication extension(Map<String, Object> extension) {
        if (Objects.isNull(extension)) {
            throw new GenerateException("extension is must not empty! ");
        }
        application.setExtension(extension);
        return this;
    }

    public void execute(Class<?>... target) {
        if (Objects.isNull(target) || target.length < 1) {
            throw new GenerateException("target is must not empty!");
        }
        for (Class<?> aClass : target) {
            execute(aClass);
        }
    }


    /**
     * 执行接口
     *
     * @param target 生成类
     */
    public void execute(Class<?> target) {
        TemplateContextPool.reset();
        if (Objects.isNull(target)) {
            throw new GenerateException("target is must not null!");
        }
        GenInfoAnnotation genInfoAnnotation = AnnotationUtil.getAnnotation(target, GenInfoAnnotation.class);
        if (Objects.isNull(genInfoAnnotation)) {
            throw new GenerateException("target cannot be without a GenInfoAnnotation");
        }
        Module module = AnnotationUtil.getAnnotation(target, Module.class);
        String moduleName = "";
        if (Objects.nonNull(module) && !StrUtil.isBlank(module.value())) {
            moduleName = module.value();
        }
        application.setModuleName(moduleName);
        application.setModuleDesc(module.desc());
        application.setClassName(ClassUtil.getClassName(target, true));
        application.setUrl(genInfoAnnotation.url());
        application.setTableName(genInfoAnnotation.tableName());
        application.setTableComment(genInfoAnnotation.tableComment());
        application.setTableType(genInfoAnnotation.tableType());
        application.setTableSchema(genInfoAnnotation.tableSchema());
        application.setEngine(genInfoAnnotation.engine());
        application.setCreateTime(genInfoAnnotation.createTime());
        application.setUpdateTime(genInfoAnnotation.updateTime());
        Field[] declaredFields = ClassUtil.getDeclaredFields(target);
        Map<String, TemplateInfo> templateInfos = new HashMap<>();
        for (Field field : declaredFields) {
            TemplateContextPool.execute(templateInfos, field, application);
        }
        application.setTemplateInfos(new ArrayList<>(templateInfos.values()));
        GeneratorUtils.writeCode(application);
    }

}
