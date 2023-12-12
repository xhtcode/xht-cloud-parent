package com.xht.cloud.framework.exception;


import com.xht.cloud.framework.exception.business.BizException;
import com.xht.cloud.framework.exception.factory.ExceptionFactory;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 描述 ：自定义异常断言类
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.notEmpty(clazz, "The value must be greater than zero");</pre>
 * <p>
 * 参考  {@link org.springframework.util.Assert}
 *
 * @author : 小糊涂
 * @see com.xht.cloud.framework.exception.enums.GlobalErrorStatusCode
 * @see BizException
 **/
public abstract class Assert {

    /**
     * 字符串为空抛出异常
     *
     * @param str 字符串
     */
    public static void hasText(String str) {
        hasText(str, "[Assertion failed] Must not empty");
    }

    /**
     * 字符串为空抛出异常
     *
     * @param str       字符串
     * @param errorCode 异常状态码
     */
    public static void hasText(String str, IErrorStatusCode errorCode) {
        if (!StringUtils.hasText(str)) {
            fail(errorCode);
        }
    }

    /**
     * 字符串为空抛出异常
     *
     * @param str        字符串
     * @param errMessage 异常描述
     */
    public static void hasText(String str, String errMessage) {
        if (!StringUtils.hasText(str)) {
            fail(errMessage);
        }
    }

    /**
     * 对象不为空抛出异常
     *
     * @param object    对象
     * @param errorCode 异常状态码
     */
    public static void notNull(Object object, IErrorStatusCode errorCode) {
        if (Objects.isNull(object)) {
            fail(errorCode);
        }
    }

    /**
     * 对象不为空抛出异常
     *
     * @param object     对象
     * @param errMessage 异常描述
     */
    public static void notNull(Object object, String errMessage) {
        if (Objects.isNull(object)) {
            fail(errMessage);
        }
    }

    /**
     * 对象不为空抛出异常
     *
     * @param object 对象
     */
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] Must not null");
    }


    /**
     * 集合为空排除异常
     *
     * @param collection 集合
     * @param errorCode  异常状态码
     */
    public static void notEmpty(Collection<?> collection, IErrorStatusCode errorCode) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            fail(errorCode);
        }
    }

    /**
     * 集合为空排除异常
     *
     * @param collection 集合
     * @param errMessage 异常信息
     */
    public static void notEmpty(Collection<?> collection, String errMessage) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            fail(errMessage);
        }
    }

    /**
     * 集合为空排除异常
     *
     * @param collection 集合
     */
    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] Collection must not be empty: it must contain at least 1 element");
    }

    /**
     * map集合为空排除异常
     *
     * @param map       map集合
     * @param errorCode 异常状态码
     */
    public static void notEmpty(Map<?, ?> map, IErrorStatusCode errorCode) {
        if (Objects.isNull(map) || map.isEmpty()) {
            fail(errorCode);
        }
    }

    /**
     * map集合为空排除异常
     *
     * @param map        map集合
     * @param errMessage 异常信息
     */
    public static void notEmpty(Map<?, ?> map, String errMessage) {
        if (Objects.isNull(map) || map.isEmpty()) {
            fail(errMessage);
        }
    }

    /**
     * map集合为空排除异常
     *
     * @param map map集合
     */
    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] Map must not be empty: it must contain at least one entry");
    }

    /**
     * 直接抛出异常
     *
     * @param s 异常描述信息
     */
    public static void fail(String s) {
        throw ExceptionFactory.bizException(s);
    }

    /**
     * 直接抛出异常
     *
     * @param errorStatusCode 异常状态码
     */
    public static void fail(IErrorStatusCode errorStatusCode) {
        throw ExceptionFactory.bizException(errorStatusCode);
    }


    /**
     * 断言是否为假，如果为 true 抛出{@link BizException}
     *
     * @param flag    布尔值
     * @param message 错误信息
     */
    public static void isFalse(boolean flag, String message) {
        isFalse(flag, () -> ExceptionFactory.bizException(message));
    }


    /**
     * 断言是否为假，如果为 {@code true} 抛出指定类型异常<br>
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     *  Assert.isFalse(i &gt; 0, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param <X>           异常类型
     * @param expression    布尔值
     * @param errorSupplier 指定断言不通过时抛出的异常
     * @throws X if expression is {@code false}
     */
    public static <X extends Throwable> void isFalse(boolean expression, Supplier<X> errorSupplier) throws X {
        if (expression) {
            throw errorSupplier.get();
        }
    }
}
