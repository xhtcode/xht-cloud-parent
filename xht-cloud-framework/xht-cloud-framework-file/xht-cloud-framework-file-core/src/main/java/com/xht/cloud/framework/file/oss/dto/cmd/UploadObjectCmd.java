package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.file.core.enums.ContentType;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public class UploadObjectCmd {

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
     * 具体文件，完整的路径
     */
    private final String fileName;

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

    UploadObjectCmd(String bucketName, String region, String objectName, String fileName, ContentType contentType, Map<String, String> tags, Map<String, String> requestHeaders, Map<String, String> metadata, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.bucketName = bucketName;
        this.region = region;
        this.objectName = objectName;
        this.fileName = fileName;
        this.contentType = contentType;
        this.tags = tags;
        this.requestHeaders = requestHeaders;
        this.metadata = metadata;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }

    public static UploadObjectCmdBuilder builder() {
        return new UploadObjectCmdBuilder();
    }

    public static class UploadObjectCmdBuilder {
        private String bucketName;
        private String region;
        private String objectName;
        private String fileName;
        private ContentType contentType = ContentType.OCTET_STREAM;
        private final Map<String, String> tags = new LinkedHashMap<>();
        private final Map<String, String> requestHeaders = new LinkedHashMap<>();
        private final Map<String, String> metadata = new LinkedHashMap<>();
        private final Map<String, String> extraHeaders = new LinkedHashMap<>();
        private final Map<String, String> extraQueryParams = new LinkedHashMap<>();

        UploadObjectCmdBuilder() {
        }

        public UploadObjectCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public UploadObjectCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public UploadObjectCmdBuilder objectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public UploadObjectCmdBuilder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public UploadObjectCmdBuilder contentType(ContentType contentType) {
            Assert.notNull(contentType);
            this.contentType = contentType;
            return this;
        }

        public UploadObjectCmdBuilder tags(Map<String, String> tags) {
            this.tags.putAll(tags);
            return this;
        }

        public UploadObjectCmdBuilder requestHeaders(Map<String, String> requestHeaders) {
            this.requestHeaders.putAll(requestHeaders);
            return this;
        }

        public UploadObjectCmdBuilder metadata(Map<String, String> metadata) {
            this.metadata.putAll(metadata);
            return this;
        }

        public UploadObjectCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public UploadObjectCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }


        public UploadObjectCmdBuilder tags(String key, String value) {
            this.tags.put(key, value);
            return this;
        }

        public UploadObjectCmdBuilder requestHeaders(String key, String value) {
            this.requestHeaders.put(key, value);
            return this;
        }

        public UploadObjectCmdBuilder metadata(String key, String value) {
            this.metadata.put(key, value);
            return this;
        }

        public UploadObjectCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public UploadObjectCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }

        public UploadObjectCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.objectName), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.fileName), () -> new OssException(OssErrorStatusCode.SAVE_FILE_PATH_ERROR));
            return new UploadObjectCmd(this.bucketName, this.region, this.objectName, this.fileName, this.contentType, this.tags, this.requestHeaders, this.metadata, this.extraHeaders, this.extraQueryParams);
        }

    }
}
