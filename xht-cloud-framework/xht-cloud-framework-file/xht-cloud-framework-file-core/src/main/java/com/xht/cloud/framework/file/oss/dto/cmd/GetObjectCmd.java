package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public class GetObjectCmd {

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
     * 版本ID
     */
    private final String versionId;

    /**
     * 偏移 offset
     */
    private final Long offset;

    /**
     * 长度 length
     */
    private final Long length;

    /**
     * ETag值反向匹配约束列表
     */
    private final List<String> notMatchEtag;

    /**
     * ETag值匹配约束列表
     */
    private final List<String> matchETag;

    /**
     * 修改时间匹配约束
     */
    private final LocalDateTime modifiedSince;

    /**
     * 修改时间反向匹配约束
     */
    private final LocalDateTime unmodifiedSince;

    /**
     * 额外的请求头
     */
    private final Map<String, String> extraHeaders;

    /**
     * 额外的Query参数
     */
    private final Map<String, String> extraQueryParams;

    GetObjectCmd(String bucketName, String objectName, String region, String versionId, Long offset, Long length, List<String> notMatchEtag, List<String> matchETag, LocalDateTime modifiedSince, LocalDateTime unmodifiedSince, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.bucketName = bucketName;
        this.objectName = objectName;
        this.region = region;
        this.versionId = versionId;
        this.offset = offset;
        this.length = length;
        this.notMatchEtag = notMatchEtag;
        this.matchETag = matchETag;
        this.modifiedSince = modifiedSince;
        this.unmodifiedSince = unmodifiedSince;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }

    public static GetObjectCmdBuilder builder() {
        return new GetObjectCmdBuilder();
    }

    public static class GetObjectCmdBuilder {

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
         * 版本ID
         */
        private String versionId;

        /**
         * 偏移 offset
         */
        private Long offset;

        /**
         * 长度 length
         */
        private Long length;

        /**
         * ETag值反向匹配约束列表
         */
        private final List<String> notMatchEtag = new ArrayList<>();

        /**
         * ETag值匹配约束列表
         */
        private final List<String> matchETag = new ArrayList<>();

        /**
         * 修改时间匹配约束
         */
        private LocalDateTime modifiedSince;

        /**
         * 修改时间反向匹配约束
         */
        private LocalDateTime unmodifiedSince;

        /**
         * 额外的请求头
         */
        private final Map<String, String> extraHeaders = new LinkedHashMap<>();

        /**
         * 额外的Query参数
         */
        private final Map<String, String> extraQueryParams = new LinkedHashMap<>();

        GetObjectCmdBuilder() {
        }

        public GetObjectCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public GetObjectCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public GetObjectCmdBuilder objectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public GetObjectCmdBuilder versionId(String versionId) {
            this.versionId = versionId;
            return this;
        }

        public GetObjectCmdBuilder offset(Long offset) {
            this.offset = offset;
            return this;
        }

        public GetObjectCmdBuilder length(Long length) {
            this.length = length;
            return this;
        }

        public GetObjectCmdBuilder notMatchEtag(List<String> notMatchEtag) {
            Assert.notEmpty(notMatchEtag, OssErrorStatusCode.BUSINESS_ERROR);
            this.notMatchEtag.addAll(notMatchEtag);
            return this;
        }

        public GetObjectCmdBuilder matchETag(List<String> matchETag) {
            Assert.notEmpty(matchETag, OssErrorStatusCode.BUSINESS_ERROR);
            this.matchETag.addAll(matchETag);
            return this;
        }

        public GetObjectCmdBuilder notMatchEtag(String notMatchEtag) {
            Assert.hasText(notMatchEtag);
            this.matchETag.add(notMatchEtag);
            return this;
        }

        public GetObjectCmdBuilder matchETag(String matchETag) {
            Assert.hasText(matchETag);
            this.matchETag.add(matchETag);
            return this;
        }


        public GetObjectCmdBuilder modifiedSince(LocalDateTime modifiedSince) {
            this.modifiedSince = modifiedSince;
            return this;
        }

        public GetObjectCmdBuilder unmodifiedSince(LocalDateTime unmodifiedSince) {
            this.unmodifiedSince = unmodifiedSince;
            return this;
        }

        public GetObjectCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public GetObjectCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }

        public GetObjectCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public GetObjectCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }

        public GetObjectCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.objectName), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
            return new GetObjectCmd(this.bucketName, this.objectName, this.region, this.versionId, this.offset, this.length, this.notMatchEtag, this.matchETag, this.modifiedSince, this.unmodifiedSince, this.extraHeaders, this.extraQueryParams);
        }

    }
}
