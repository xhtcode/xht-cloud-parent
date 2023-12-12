package com.xht.cloud.code.generate.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.xht.cloud.code.generate.config.ConfigInfo;
import com.xht.cloud.code.generate.config.GenCodeInfo;
import com.xht.cloud.code.generate.constant.GenCodeConstant;
import com.xht.cloud.code.generate.jdbc.model.Table;
import com.xht.cloud.code.generate.template.PackageFactory;
import com.xht.cloud.code.generate.template.model.TemplateColumn;
import com.xht.cloud.code.generate.template.model.TemplateInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 ： 代码生成工具类
 *
 * @author : 小糊涂
 **/
@Slf4j
public class GeneratorUtils {
    /**
     * 获取
     *
     * @param tableName 表明
     * @return String
     */
    public static String getModuleName(String tableName) {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        return StrUtil.sub(tableName, lastIndex + 1, nameLength);
    }

    /**
     * @param template 模板名称
     * @param info     表信息
     * @return 代码
     */
    public static String initCode(String template, Table info, ConfigInfo config) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template t = ve.getTemplate(template);
        VelocityContext ctx = new VelocityContext();
        ctx.put("moduleName", getModuleName(info.getTableName()));
        ctx.put("packageName", config.getPackageName());
        ctx.put("author", config.getAuthor());
        ctx.put("url", StrUtil.addPrefixIfNot(StringUtils.replace(info.getTableName(), "_", "/"), "/"));
        ctx.put("tableSchema", info.getTableSchema());
        ctx.put("tableName", info.getTableName());
        ctx.put("tableNameHump", info.getTableNameHump());
        ctx.put("tableNameHumpUpperFirst", info.getTableNameHumpUpperFirst());
        ctx.put("tableNameHumpLowerFirst", info.getTableNameHumpLowerFirst());
        ctx.put("engine", info.getEngine());
        ctx.put("tableComment", info.getTableComment());
        ctx.put("tableType", info.getTableType());
        ctx.put("createTime", LocalDateTimeUtil.format(info.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        ctx.put("updateTime", Convert.toStr(LocalDateTimeUtil.format(info.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"), ""));
        ctx.put("isView", info.isView());
        ctx.put("columns", info.getColumns());
        StringWriter stringWriter = new StringWriter();
        t.merge(ctx, stringWriter);
        return stringWriter.toString();
    }

    public static void writeCode(GenCodeInfo codeInfo) {
        List<TemplateInfo> templateInfos = codeInfo.getTemplateInfos();
        String moduleName = codeInfo.getModuleName();
        for (TemplateInfo templateInfo : templateInfos) {
            log.info("===========================渲染template/{}模板开始===========================", templateInfo.getTemplatePath());
            Map<String, String> paramsMap1 = new HashMap<>();
            paramsMap1.put("moduleName", moduleName);
            String packageName = StrFormatter.format(templateInfo.getPackageName(), paramsMap1, false);
            templateInfo.setPackageName(packageName);
            String code = initCode(codeInfo, templateInfo);
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("packageName", StrUtil.replace(packageName, ".", "/"));
            paramsMap.put("moduleName", moduleName);
            String[] replace = StrUtil.toUnderlineCase(codeInfo.getClassName()).split("_");
            paramsMap.put("webPackage", replace[replace.length - 1]);
            paramsMap.put("fileName", templateInfo.getFileName());
            paramsMap.put("fileNameLower", templateInfo.getFileName().toLowerCase());
            String codePath = FileUtil.getAbsolutePath(StrFormatter.format(templateInfo.getPath(), paramsMap, false));
            log.info("===========================渲染template/{}模板结束===========================\n代码路径:{}"
                    , templateInfo.getTemplatePath()
                    , codePath
            );
            if (!templateInfo.isReaddition()) {
                FileUtil.writeString(code, new File(codePath), StandardCharsets.UTF_8);
            } else {
                FileUtil.appendString(code, new File(codePath), StandardCharsets.UTF_8);
            }

        }
    }

    public static String getJavaPath(Class<?> target) {
        return ClassUtil.getLocationPath(target)
                .replace(GenCodeConstant.TARGET_CLASSES, GenCodeConstant.EMPTY)
                .replace(GenCodeConstant.TARGET_TEST_CLASSES, GenCodeConstant.EMPTY)
                ;
    }

    private static String initCode(GenCodeInfo codeInfo, TemplateInfo templateInfo) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template t = ve.getTemplate(StrFormatter.format("template/{}", templateInfo.getTemplatePath()));
        VelocityContext ctx = new VelocityContext();
        ctx.put("moduleName", codeInfo.getModuleName());
        ctx.put("moduleDesc", codeInfo.getModuleDesc());
        ctx.put("perms", getPerms(codeInfo.getTableName()));//权限前缀
        ctx.put("url", StrUtil.addPrefixIfNot(StringUtils.replace(codeInfo.getTableName(), "_", "/"), "/"));
        ctx.put("className", codeInfo.getClassName());
        ctx.put("classNameFirstLower", codeInfo.getClassNameFirstLower());
        ctx.put("tableName", codeInfo.getTableName());
        ctx.put("tableComment", codeInfo.getTableComment());
        ctx.put("tableType", codeInfo.getTableType());
        ctx.put("tableSchema", codeInfo.getTableSchema());
        ctx.put("engine", codeInfo.getEngine());
        ctx.put("createTime", codeInfo.getCreateTime());
        ctx.put("updateTime", codeInfo.getUpdateTime());
        ConfigInfo configInfo = codeInfo.getConfigInfo();
        ctx.put("packageName", templateInfo.getPackageName());
        ctx.put("author", configInfo.getAuthor());
        Map<String, Object> extension = codeInfo.getExtension();
        if (Objects.nonNull(extension)) {
            extension.forEach(ctx::put);
        }
        Map<String, Object> templateInfoExtension = templateInfo.getExtension();
        if (Objects.nonNull(templateInfoExtension)) {
            templateInfoExtension.forEach(ctx::put);
        }
        ctx.put("columns", templateInfo.getColumns());
        ctx.put("pkColumn", templateInfo.getColumns().stream().filter(item -> item.getPk().equals("1")).findFirst().orElse(TemplateColumn.builder().build()));
        StringWriter stringWriter = new StringWriter();
        t.merge(ctx, stringWriter);
        return stringWriter.toString();
    }

    private static String getPerms(String tableName) {
        String s = StrUtil.replaceFirst(tableName, "_", ":");
        return StrUtil.replace(s, "_", "-");
    }

    public static void execute(GenCodeInfo application, String getPageName, String fileName, String name) {
        Map<String, String> paramsMap1 = new HashMap<>();
        paramsMap1.put("moduleName", application.getModuleName());
        String packageName = StrFormatter.format(getPageName, paramsMap1, false);
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("packageName", packageName.replace(".", "/"));
        paramsMap.put("moduleName", application.getModuleName());
        paramsMap.put("className", application.getClassName());
        String[] replace = StrUtil.toUnderlineCase(application.getClassName()).split("_");
        paramsMap.put("webPackage", replace[replace.length - 1]);
        paramsMap.put("fileName", fileName);
        String codePath = StrFormatter.format("@/{packageName}/{webPackage}/{fileName}", paramsMap, false);
        PackageFactory.addPath(name, codePath);
    }

    public static String getTsType(final String javaType) {
        String result;
        switch (javaType.toLowerCase()) {
            case "char", "string" -> {
                result = "string";
            }
            case "int", "integer", "float", "double" -> {
                result = "number";
            }
            case "boolean" -> {
                result = "boolean";
            }
            default -> {
                result = "any";
            }
        }
        return result;
    }

}
