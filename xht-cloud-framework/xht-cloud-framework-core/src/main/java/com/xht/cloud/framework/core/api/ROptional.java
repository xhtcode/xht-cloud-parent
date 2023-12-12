package com.xht.cloud.framework.core.api;

import com.xht.cloud.framework.exception.Assert;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * 描述 ：简化R<T> 的访问操作
 *
 * @author : 小糊涂
 **/
public final class ROptional<T> implements Serializable {
    private final R<T> original;

    private ROptional(R<T> original) {
        this.original = original;
    }

    /**
     * 初始化
     *
     * @param value 原始 不能为空
     * @param <T>      {@link Serializable}
     * @return this
     */
    public static <T> ROptional<T> of(R<T> value) {
        Assert.notNull(value, "original is value empty!");
        return new ROptional<>(value);
    }

    /**
     * 初始化
     *
     * @param value 原始 能为空
     * @param <T>      {@link Serializable}
     * @return this
     */
    public static <T> ROptional<T> ofNullable(R<T> value) {
        return new ROptional<>(value);
    }


    /**
     * 读取{@code code}的值
     *
     * @return 返回code的值
     */
    public Integer getCode() {
        if (Objects.isNull(original)) {
            return null;
        }
        return original.getCode();
    }


    /**
     * 读取{@code data}的值
     *
     * @return 返回 Optional 包装的data
     */
    public Optional<T> getData() {
        if (Objects.isNull(original)) {
            return Optional.empty();
        }
        return Optional.ofNullable(original.getData());
    }

    /**
     * 是否成功
     *
     * @return 返回ture表示成功
     * @see ApiConstants#SUCCESS
     */
    public boolean isSuccess() {
        return codeEquals(ApiConstants.SUCCESS);
    }

    /**
     * 对{@code code}的值进行相等性测试
     *
     * @param value 基准值
     * @return 返回ture表示相等
     */
    public boolean codeEquals(Integer value) {
        notEmpty();
        return Objects.equals(original.getCode(), value);
    }

    /**
     * 判断是否为空 为空就抛出异常
     */
    private void notEmpty() {
        Assert.notNull(original, " original is null, You can't use this method!");
    }

}
