package com.xht.cloud.code.generate.template.strategy.sql;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.xht.cloud.code.generate.annotation.file.GenSql;
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
public class TemplateContextSql extends TemplateContext<GenSql> {

    private final String menuParentId;

    public TemplateContextSql(final String packageName, final String path, final String menuParentId) {
        super("", "menu", FileType.SQL, "sql/sql.vm", packageName, path);
        super.setReaddition(true);
        this.menuParentId = Convert.toStr(menuParentId, "-1");
    }

    /**
     * 是否要执行
     *
     * @param annotation 注解
     */
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof GenSql;
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
    public void execute(TemplateColumn.TemplateColumnBuilder builder, GenSql annotation, Field filedInfo) {
        setFileNameOnly(true);
        for (int i = 0; i < 10; i++) {
            super.extension.put("id" + i, IdUtil.getSnowflake().nextIdStr());
        }
        super.extension.put("indexPackage", String.valueOf(PackageFactory.getPath("indexPackage")).replace("@",""));
        super.extension.put("menuParentId", menuParentId);
    }

    /**
     * 排序接口 数值越小级别越高
     *
     * @return 数值
     */
    @Override
    public int order() {
        return 6;
    }
}
