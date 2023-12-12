package com.xht.cloud.framework.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 ：系统操作日志注解
 *
 * @author : 小糊涂
 **/
@Documented //用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化.Documented是一个标记注解,没有成员.
@Target(ElementType.METHOD) //指定被修饰的Annotation可以放置的位置(被修饰的目标)类，方法，属性
@Retention(RetentionPolicy.RUNTIME) //定义注解的保留策略, RetentionPolicy.RUNTIME：注解会在class字节码文件中存在,在运行时可以通过反射获取到
public @interface SysLog {

    /**
     * 业务名称
     */
    String busName();

    /**
     * 业务描述
     */
    String description() default "暂无";

}
