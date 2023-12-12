package com.xht.cloud.file.oss.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@ConfigurationProperties(prefix = "xht.oss.minio")
public class MinioProperties {

    /**
     * host
     */
    private String host;

    /**
     * 公钥
     */
    private String accessKey;

    /**
     * 私钥
     */
    private String secretKey;

    /**
     * 存储桶
     */
    private String bucketName;

}
