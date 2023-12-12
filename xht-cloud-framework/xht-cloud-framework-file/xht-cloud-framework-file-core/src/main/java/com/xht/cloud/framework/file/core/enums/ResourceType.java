package com.xht.cloud.framework.file.core.enums;

import com.xht.cloud.framework.core.enums.IEnum;
import lombok.Getter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public enum ResourceType implements IEnum<String> {

    STATIC_RESOURCE("0", "静态资源"),

    SERVER_RESOURCE("1", "业务资源"),

    PUBLIC_RESOURCE("2", "公共资源"),
    ;
    private final String code;

    @Getter
    private final String value;

    ResourceType(String code, String value) {
        this.code = code;
        this.value = value;
    }

}
