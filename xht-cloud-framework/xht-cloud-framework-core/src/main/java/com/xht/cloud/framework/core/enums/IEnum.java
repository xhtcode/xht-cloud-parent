package com.xht.cloud.framework.core.enums;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Objects;

/**
 * 描述 ： 自定义枚举接口
 *
 * @author : 小糊涂
 **/
public interface IEnum<T extends Serializable> extends ValueEnum<T>, SelfDescribedEnum {

    static <E extends Enum<E> & IEnum<?>, T extends Serializable> E getIEnum(T value, Class<E> clazz) {
        if (Objects.isNull(value)) {
            return null;
        }
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> Objects.equals(e.getValue(), value)).findFirst().orElse(null);
    }
}
