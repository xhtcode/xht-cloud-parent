package com.xht.cloud.code.generate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 ： 主信息
 * <p>
 * 有以下内容：
 * <li>业务描述: tableName</li>
 * <li>表描述: tableComment</li>
 * <li>表类型: tableType</li>
 * <li>表所在实例: tableSchema</li>
 * <li>数据库引擎: engine</li>
 * <li>表创建时间: createTime</li>
 * <li>表更新时间: updateTime</li>
 *
 * @author : 小糊涂
 **/

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GenInfoAnnotation {

    /**
     * 业务描述
     */
    String tableName();

    /**
     * url路径
     */
    String url();


    /**
     * 表描述
     */
    String tableComment() default "无";

    /**
     * 表类型
     */
    String tableType() default "BASE TABLE";


    /**
     * 表所在实例
     */
    String tableSchema() default "test";

    /**
     * 数据库引擎
     */
    String engine() default "InnoDB";

    /**
     * 表创建时间
     */
    String createTime() default "";

    /**
     * 表更新时间
     */
    String updateTime() default "";



}
