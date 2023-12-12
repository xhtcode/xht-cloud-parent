package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.file.core.enums.ContentType;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public class PutObjectCmd {

    /**
     * 文件流
     */
    private final InputStream stream;

    /**
     * 存储桶名称
     */
    private final String bucketName;

    /**
     * 区域
     */
    private final String region;

    /**
     * 对象名称
     */
    private final String objectName;

    /**
     * 对象大小
     */
    private final Long objectSize;

    /**
     * 分片大小 -1 表示全部
     */
    private final Long partSize;

    /**
     * 内容类型
     */
    private final ContentType contentType;

    /**
     * 标签
     */
    private final Map<String, String> tags;

    /**
     * 请求头信息
     */
    private final Map<String, String> requestHeaders;

    /**
     * 对象元数据
     */
    private final Map<String, String> metadata;

    /**
     * 额外的请求头
     */
    private final Map<String, String> extraHeaders;

    /**
     * 额外的Query参数
     */
    private final Map<String, String> extraQueryParams;

    PutObjectCmd(InputStream stream, String bucketName, String region, String objectName, Long objectSize, Long partSize, ContentType contentType, Map<String, String> tags, Map<String, String> requestHeaders, Map<String, String> metadata, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.stream = stream;
        this.bucketName = bucketName;
        this.region = region;
        this.objectName = objectName;
        this.objectSize = objectSize;
        this.partSize = partSize;
        this.contentType = contentType;
        this.tags = tags;
        this.requestHeaders = requestHeaders;
        this.metadata = metadata;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }

    public static PutObjectCmdBuilder builder() {
        return new PutObjectCmdBuilder();
    }
    public static class PutObjectCmdBuilder {
        private InputStream stream;
        private String bucketName;
        private String region;
        private String objectName;
        private Long objectSize;
        private Long partSize;
        private ContentType contentType = ContentType.OCTET_STREAM;
        private final Map<String, String> tags = new LinkedHashMap<>();
        private final Map<String, String> requestHeaders = new LinkedHashMap<>();
        private final Map<String, String> metadata = new LinkedHashMap<>();
        private final Map<String, String> extraHeaders = new LinkedHashMap<>();
        private final Map<String, String> extraQueryParams = new LinkedHashMap<>();

        PutObjectCmdBuilder() {
        }

        @SneakyThrows
        public PutObjectCmdBuilder stream(InputStream stream) {
            this.stream = stream;
            this.objectSize = (long) stream.available();
            this.partSize = -1L;
            return this;
        }

        public PutObjectCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public PutObjectCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public PutObjectCmdBuilder objectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public PutObjectCmdBuilder objectSize(Long objectSize) {
            this.objectSize = objectSize;
            return this;
        }

        public PutObjectCmdBuilder partSize(Long partSize) {
            this.partSize = partSize;
            return this;
        }

        public PutObjectCmdBuilder contentType(ContentType contentType) {
            Assert.notNull(contentType);
            this.contentType = contentType;
            return this;
        }

        public PutObjectCmdBuilder tags(Map<String, String> tags) {
            this.tags.putAll(tags);
            return this;
        }

        public PutObjectCmdBuilder requestHeaders(Map<String, String> requestHeaders) {
            this.requestHeaders.putAll(requestHeaders);
            return this;
        }

        public PutObjectCmdBuilder metadata(Map<String, String> metadata) {
            this.metadata.putAll(metadata);
            return this;
        }

        public PutObjectCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public PutObjectCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }


        public PutObjectCmdBuilder tags(String key, String value) {
            this.tags.put(key, value);
            return this;
        }

        public PutObjectCmdBuilder requestHeaders(String key, String value) {
            this.requestHeaders.put(key, value);
            return this;
        }

        public PutObjectCmdBuilder metadata(String key, String value) {
            this.metadata.put(key, value);
            return this;
        }

        public PutObjectCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public PutObjectCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }


        public PutObjectCmd build() {
            Assert.notNull(this.stream, OssErrorStatusCode.OBJECT_STREAM_ERROR);
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.objectName), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
            Assert.isFalse(Objects.isNull(this.stream), () -> new OssException(OssErrorStatusCode.OBJECT_STREAM_ERROR));
            return new PutObjectCmd(this.stream, this.bucketName, this.region, this.objectName, this.objectSize, this.partSize, this.contentType, this.tags, this.requestHeaders, this.metadata, this.extraHeaders, this.extraQueryParams);
        }

    }
}
