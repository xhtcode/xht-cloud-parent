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
public class DeleteObjectsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 对象名称
     */
    private String objectName;
    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 存储桶名称
     */
    private String bucketName;
    /**
     * 资源名称
     */
    private String resource;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 主机ID
     */
    private String hostId;
}
