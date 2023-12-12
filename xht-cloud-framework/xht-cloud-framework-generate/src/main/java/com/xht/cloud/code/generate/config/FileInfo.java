package com.xht.cloud.code.generate.config;

import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@Builder
public class FileInfo {

    private String fieldName;

    private Field field;

    private Annotation annotation;

    private String annotationClass;

}
