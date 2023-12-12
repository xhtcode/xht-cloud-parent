package com.xht.cloud.code.generate.config;

import cn.hutool.core.util.StrUtil;
import com.xht.cloud.code.generate.exception.GenerateException;
import com.xht.cloud.code.generate.template.model.TemplateInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class GenCodeInfo {

    /**
     * 只用文件名
     */
    private boolean fileNameOnly;

    public boolean isFileNameOnly() {
        return fileNameOnly;
    }

    public void setFileNameOnly(boolean fileNameOnly) {
        this.fileNameOnly = fileNameOnly;
    }

    private String url;

    private String className;

    private String classNameFirstLower;

    /**
     * 业务描述
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 表类型
     */
    private String tableType;


    /**
     * 表所在实例
     */
    private String tableSchema;

    /**
     * 数据库引擎
     */
    private String engine;

    /**
     * 表创建时间
     */
    private String createTime;

    /**
     * 表更新时间
     */
    private String updateTime;


    private ConfigInfo configInfo;

    private List<TemplateInfo> templateInfos;

    private String moduleName;
    private String moduleDesc;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    private Map<String, Object> extension = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public List<TemplateInfo> getTemplateInfos() {
        return templateInfos;
    }

    public void setTemplateInfos(List<TemplateInfo> templateInfos) {
        this.templateInfos = templateInfos;
    }

    public Map<String, Object> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, Object> extension) {
        if (Objects.isNull(extension)) {
            throw new GenerateException("extension is must not empty!");
        }
        this.extension = extension;
    }

    public void addExtension(Map<String, Object> extension) {
        if (Objects.isNull(extension)) {
            throw new GenerateException("extension is must not empty!");
        }
        this.extension.putAll(extension);
    }

    public void removeExtension(Map<String, Object> extension) {
        if (Objects.isNull(extension)) {
            throw new GenerateException("extension is must not empty!");
        }
        extension.keySet().forEach(key -> this.extension.remove(key));
    }


    public void setClassName(String className) {
        this.className = className;
        this.classNameFirstLower = StrUtil.lowerFirst(className);
    }

    public String getClassName() {
        return className;
    }

    public String getClassNameFirstLower() {
        return classNameFirstLower;
    }


}
