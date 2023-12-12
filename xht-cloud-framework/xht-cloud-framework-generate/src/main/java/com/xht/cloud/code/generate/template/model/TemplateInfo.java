package com.xht.cloud.code.generate.template.model;

import cn.hutool.core.util.StrUtil;
import com.xht.cloud.code.generate.exception.GenerateException;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 ：模板信息
 *
 * @author : 小糊涂
 **/
@Builder
public class TemplateInfo implements Serializable {
    private final Map<String, Object> extension = new HashMap<>();

    public TemplateInfo addExtension(Map<String, Object> extension) {
        if (Objects.isNull(extension)) {
            throw new GenerateException("extension is must not empty!");
        }
        this.extension.putAll(extension);
        return this;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模板前缀
     */
    private String pathPrefix;

    /**
     * 模板后缀
     */
    private String pathSuffix;

    /**
     * 模板路径
     */
    private String templatePath;

    private String fileName;

    private String path;

    /**
     * 模板字段
     */
    private List<TemplateColumn> columns;

    private String packageName;

    private String annotation;
    /**
     * 是否追加
     */
    private boolean readdition;

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Map<String, Object> getExtension() {
        return extension;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }


    public String getPathSuffix() {
        return pathSuffix;
    }


    public String getTemplatePath() {
        return templatePath;
    }


    public String getPath() {
        return StrUtil.removePrefixIgnoreCase(path, "/");
    }


    public List<TemplateColumn> getColumns() {
        return columns;
    }


    public boolean isReaddition() {
        return readdition;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFileName() {
        return fileName;
    }
}
