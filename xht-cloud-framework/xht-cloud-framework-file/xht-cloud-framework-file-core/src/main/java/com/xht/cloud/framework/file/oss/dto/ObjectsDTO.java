package com.xht.cloud.framework.file.oss.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class ObjectsDTO extends BucketDTO {

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 存储区域
     */
    private String region;

    /**
     * 存储此对象的密钥
     */
    private String objectName;

    /**
     * ETag。此对象内容的十六进制编码MD5哈希
     */
    private String eTag;

    /**
     * 此对象的大小，以字节为单位
     */
    private Long size;

    /**
     * 对象最后一次被修改的日期
     */
    private LocalDateTime lastModified;

    /**
     * 存储此对象的存储类
     */
    private String storageClass;

    private Boolean isDir;

}
