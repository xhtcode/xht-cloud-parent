package com.xht.cloud.framework.file.oss.convert;

import org.springframework.core.convert.converter.Converter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public interface OssConverter<S, T> extends Converter<S, T> {

    /**
     * 反向转换 T TO S
     */
    default S reversal(T target) {
        throw new RuntimeException("OssConverter co");
    }

}
