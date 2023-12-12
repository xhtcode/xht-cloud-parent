package com.xht.cloud.framework.core.enums;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述 ：枚举公共返回对象
 *
 * @author : 小糊涂
 **/
public record CommonEnumDto<T extends Serializable>(T value, String name, String desc) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static <E extends Serializable> CommonEnumDto<E> format(IEnum<E> commonEnum) {
        if (commonEnum == null) {
            return null;
        }
        return new CommonEnumDto<>(commonEnum.getValue(), commonEnum.getName(), commonEnum.getDescription());
    }

    public static <E extends Serializable> List<CommonEnumDto<E>> format(List<IEnum<E>> commonEnums) {
        if (Objects.isNull(commonEnums)) {
            return Collections.emptyList();
        }
        return commonEnums.stream().map(CommonEnumDto::format).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        return desc();
    }
}
