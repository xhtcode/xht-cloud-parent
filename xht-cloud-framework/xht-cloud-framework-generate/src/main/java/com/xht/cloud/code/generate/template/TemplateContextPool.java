package com.xht.cloud.code.generate.template;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ClassUtil;
import com.xht.cloud.code.generate.annotation.fields.FiledInfo;
import com.xht.cloud.code.generate.config.GenCodeInfo;
import com.xht.cloud.code.generate.exception.GenerateException;
import com.xht.cloud.code.generate.order.Order;
import com.xht.cloud.code.generate.template.model.TemplateColumn;
import com.xht.cloud.code.generate.template.model.TemplateInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class TemplateContextPool {

    private static final List<TemplateContext<? extends Annotation>> contexts = new ArrayList<>();

    public static void register(TemplateContext<? extends Annotation> context) {
        if (Objects.isNull(context)) {
            throw new GenerateException("context is must not empty! ");
        }
        contexts.add(context);
    }

    public static void execute(Map<String, TemplateInfo> templateInfos, Field field, GenCodeInfo application) {
        CopyOnWriteArrayList<TemplateContext<? extends Annotation>> templateContexts = ListUtil.toCopyOnWriteArrayList(contexts);
        templateContexts.sort(Comparator.comparingInt(Order::order));
        FiledInfo filedInfo = AnnotationUtil.getAnnotation(field, FiledInfo.class);
        Annotation[] annotation = AnnotationUtil.getAnnotations(field, false);
        for (TemplateContext<? extends Annotation> context : templateContexts) {
            for (Annotation annotationItem : annotation) {
                if (context.support(annotationItem)) {
                    String key = annotationItem.getClass().getName() + context.getKey();
                    TemplateColumn templateColumn = context.execute(field, annotationItem, filedInfo, application);
                    if (null == templateColumn) {
                        continue;
                    }
                    if (!context.executeAfter(templateColumn)) {
                        if (templateInfos.containsKey(key)) {
                            TemplateInfo templateInfo = templateInfos.get(key);
                            templateInfo.addExtension(context.getExtension()).getColumns().add(templateColumn);
                        } else {
                            ArrayList<TemplateColumn> columns = new ArrayList<>();
                            columns.add(templateColumn);
                            TemplateInfo build = TemplateInfo.builder()
                                    .pathPrefix(context.getPathPrefix())
                                    .pathSuffix(context.getPathSuffix())
                                    .templatePath(context.getTemplatePath())
                                    .fileName(context.getFileName(application.getClassName()))
                                    .path(context.getPath())
                                    .annotation(ClassUtil.getClassName(annotationItem, false))
                                    .columns(columns)
                                    .packageName(context.getPageName())
                                    .readdition(context.isReaddition()).build();
                            build.addExtension(context.getExtension());
                            templateInfos.put(key, build);
                        }
                    }

                }
            }
        }
    }

    public static void reset() {
        contexts.forEach(TemplateContext::reset);
        PackageFactory.clear();
    }
}
