package com.xht.cloud.framework.file.oss.dto;

import lombok.Data;

import java.io.InputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class GetObjectDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private InputStream objectContent;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 存储区域
     */
    private String region;

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * 用户自定义头信息
     */
    private Map<String, Object> header = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
}
