package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public class GetPreSignedObjectUrlCmd {
    /**
     * 存储桶名称
     */
    private final String bucketName;

    /**
     * 对象名称
     */
    private final String objectName;

    /**
     * 区域
     */
    private final String region;

    /**
     * 对象保留模式 存储模式的值只能是大写
     */
    private final HttpMethod method;

    /**
     * 过期时间 单位为秒，默认值为 7 天
     */
    private final Duration expiration;

    /**
     * 版本ID
     */
    private final String versionId;

    /**
     * 额外的请求头
     */
    private final Map<String, String> extraHeaders;


    /**
     * 额外的Query参数
     */
    private final Map<String, String> extraQueryParams;

    GetPreSignedObjectUrlCmd(String bucketName, String objectName, String region, HttpMethod method, Duration expiration, String versionId, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.bucketName = bucketName;
        this.objectName = objectName;
        this.region = region;
        this.method = method;
        this.expiration = expiration;
        this.versionId = versionId;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }

    public static GetPreSignedObjectUrlCmdBuilder builder() {
        return new GetPreSignedObjectUrlCmdBuilder();
    }

    public static class GetPreSignedObjectUrlCmdBuilder {
        /**
         * 存储桶名称
         */
        private String bucketName;

        /**
         * 对象名称
         */
        private String objectName;

        /**
         * 区域
         */
        private String region;

        /**
         * 对象保留模式 存储模式的值只能是大写
         */
        private HttpMethod method = HttpMethod.PUT;

        /**
         * 过期时间 单位为秒，默认值为 7 天
         */
        private Duration expiration = Duration.ofDays(7);

        /**
         * 版本ID
         */
        private String versionId;

        /**
         * 额外的请求头
         */
        private final Map<String, String> extraHeaders = new LinkedHashMap<>();


        /**
         * 额外的Query参数
         */
        private final Map<String, String> extraQueryParams = new LinkedHashMap<>();

        GetPreSignedObjectUrlCmdBuilder() {
        }

        public GetPreSignedObjectUrlCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }


        public GetPreSignedObjectUrlCmdBuilder objectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder expiration(Duration expiration) {
            this.expiration = expiration;
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder versionId(String versionId) {
            this.versionId = versionId;
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public GetPreSignedObjectUrlCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }

        public GetPreSignedObjectUrlCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.objectName), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
            return new GetPreSignedObjectUrlCmd(this.bucketName, this.objectName, this.region, this.method, this.expiration, this.versionId, this.extraHeaders, this.extraQueryParams);
        }

    }

}
