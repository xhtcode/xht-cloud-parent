package com.xht.cloud.code.generate.template;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.xht.cloud.code.generate.annotation.fields.FiledInfo;
import com.xht.cloud.code.generate.annotation.fields.Id;
import com.xht.cloud.code.generate.config.GenCodeInfo;
import com.xht.cloud.code.generate.constant.FileType;
import com.xht.cloud.code.generate.order.Order;
import com.xht.cloud.code.generate.template.model.None;
import com.xht.cloud.code.generate.template.model.TemplateColumn;
import com.xht.cloud.code.generate.util.GeneratorUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public abstract class TemplateContext<T extends Annotation> implements Order, ITemplateContext<T> {

    private final List<String> ignoreField = new ArrayList<>();

    /**
     * 模板前缀
     */
    private final String pathPrefix;

    /**
     * 模板后缀
     */
    private final String pathSuffix;

    private final FileType fileType;

    /**
     * 模板路径
     */
    private final String templatePath;


    private final String packageName;

    /**
     * 自定义路径
     */
    private final String path;


    /**
     * 扩展信息
     */
    protected final Map<String, Object> extension = new HashMap<>();

    /**
     * 导入的包名
     */
    protected final List<String> packages = new ArrayList<>();

    /**
     * 是否追加
     */
    private boolean readdition;
    /**
     * 只用文件名
     */
    private boolean fileNameOnly;


    protected final void setFileNameOnly(boolean fileNameOnly) {
        this.fileNameOnly = fileNameOnly;
    }

    public final boolean isReaddition() {
        return readdition;
    }

    public final void setReaddition(boolean readdition) {
        this.readdition = readdition;
    }

    public final Map<String, Object> getExtension() {
        List<String> collect = packages.stream().distinct().sorted((p1, p2) -> {
            String[] s1 = p1.split("\\.");
            String[] s2 = p2.split("\\.");
            // Java SE包放在最前面
            if (s1[0].equals("java") && !s2[0].equals("java")) {
                return -1;
            } else if (!s1[0].equals("java") && s2[0].equals("java")) {
                return 1;
            }
            // 将 javax.* 包放在 java.* 包之后，其他包按字母序排序
            if (s1[0].equals("javax") && !s2[0].equals("javax")) {
                return 1;
            } else if (!s1[0].equals("javax") && s2[0].equals("javax")) {
                return -1;
            } else {
                return p1.compareTo(p2);
            }
        }).collect(Collectors.toList());
        // 排序包列表
        extension.put("importClassNames", collect);
        return extension;
    }

    public TemplateContext(String pathPrefix, String pathSuffix, FileType fileType, String templatePath, String packageName, String path) {
        this.pathPrefix = pathPrefix;
        this.pathSuffix = pathSuffix;
        this.fileType = fileType;
        this.templatePath = templatePath;
        this.packageName = packageName;
        this.path = path;
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    public abstract boolean support(Annotation annotation);


    @SuppressWarnings("unchecked")
    public final TemplateColumn execute(Field field, Annotation annotation, FiledInfo filedInfo, GenCodeInfo application) {
        if (getIgnoreField(field.getName())) {
            return null;
        }
        Id id = AnnotationUtil.getAnnotation(field, Id.class);
        if (Objects.nonNull(id)) {
            extension.put("pkSet", String.format("set%s", field.getName()));
        }
        TemplateColumn.TemplateColumnBuilder builder = TemplateColumn.builder();
        builder.columnName(field.getName())
                .columnNameUpperFirst(StrUtil.upperFirst(field.getName()))
                .columnNameDb(filedInfo.value())
                .columnNameGet(String.format("get%s", StrUtil.upperFirst(field.getName())))
                .columnNameSet(String.format("set%s", StrUtil.upperFirst(field.getName())))
                .isNullable(filedInfo.isNullable())
                .tsType(GeneratorUtils.getTsType(ClassUtil.getClassName(field.getType(), true)))
                .javaType(ClassUtil.getClassName(field.getType(), true))
                .javaTypeClass(getFileClassName(field))
                .describe(filedInfo.describe())
                .length(filedInfo.length())
                .pk(Objects.isNull(id) ? "0" : "1")
                .columnDefault(filedInfo.columnDefault());
        this.execute(builder, (T) annotation, field);
        Map<String, String> paramsMap1 = new HashMap<>();
        paramsMap1.put("moduleName", application.getModuleName());
        String packageName = StrFormatter.format(getPageName(), paramsMap1, false);
        PackageFactory.addPath(annotation.annotationType().getName() + getKey(), String.format("%s.%s%s%s", packageName, getPathPrefix(), application.getClassName(), this.pathSuffix));
        application.setFileNameOnly(this.fileNameOnly);
        execute(application);
        return builder.build();
    }


    /**
     * 执行后判断
     *
     * @param templateColumn 已经封装好的字段信息
     * @return {@link Boolean}
     */
    public boolean executeAfter(TemplateColumn templateColumn) {
        return Boolean.FALSE;
    }

    public final String getPathPrefix() {
        return this.pathPrefix;
    }

    public final String getPathSuffix() {
        return String.format("%s.%s", this.pathSuffix, fileType.getType());
    }

    public final String getTemplatePath() {
        return templatePath;
    }

    protected final String getFileClassName(Field field) {
        return field.getType().getName().contains("java.lang") ? null : field.getType().getName();
    }

    public final String getPath() {
        return path;
    }

    protected final void setPackages(String importClassName) {
        if (!StrUtil.contains(importClassName, "java.lang") && !StrUtil.isBlank(importClassName)) {
            this.packages.add(String.format("import %s;", importClassName.trim()));
        }
    }

    public final boolean getIgnoreField(final String field) {
        return this.ignoreField.contains(field.toLowerCase());
    }

    protected final void addIgnoreField(final String field) {
        this.ignoreField.add(StrUtil.toCamelCase(field).toLowerCase());
    }

    public final String getPageName() {
        return packageName;
    }

    protected final boolean getClassName(Class<?> convert) {
        return !(null == convert || Objects.equals(ClassUtil.getClassName(None.class, false), ClassUtil.getClassName(convert, false)));
    }

    public final String getFileName(String className) {
        return getPathPrefix() + (this.fileNameOnly ? "" : className) + getPathSuffix();
    }

    /**
     * 重置公共变量，防止重复
     */
    public void reset() {
        this.setFileNameOnly(false);
        this.packages.clear();
        this.extension.clear();
    }

    /**
     * 生成当前类的序列值，可以不设置 默认空字符串
     */
    public String getKey() {
        return "";
    }

}
