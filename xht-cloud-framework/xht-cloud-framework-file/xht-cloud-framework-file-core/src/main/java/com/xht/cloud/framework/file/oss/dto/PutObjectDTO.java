package com.xht.cloud.framework.file.oss.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
public class PutObjectDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 区域
     */
    private String region;

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * 文件流
     */
    private String stream;

    /**
     * 对象大小
     */
    private String objectSize;

    /**
     * 分片大小
     */
    private String partSize;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 是否保持
     */
    private String legalHold;

    /**
     * 保存设置
     */
    private String retention;

    /**
     * 标签
     */
    private String tags;

    /**
     * 服务加密
     */
    private String sse;

    /**
     * ETag 值
     */
    private String etag;

    /**
     * 版本ID
     */
    private String versionId;
}
