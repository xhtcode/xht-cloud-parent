package com.xht.cloud.framework.file.oss.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class StatObjectDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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


    /**
     * ETag 值
     */
    private String etag;

    /**
     * 版本ID
     */
    private String versionId;

    /**
     * 用户自定义 Metadata
     */
    private Map<String, String> userMetadata = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * 内容大小
     */
    private long contentLength;

    /**
     * contentType
     */
    private String contentType;

    /**
     * 最后修改时间
     */
    private LocalDateTime LastModified;
}
