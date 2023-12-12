package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
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
public class ListObjectCmd {

    /**
     * 存储桶名称
     */
    private final String bucketName;

    /**
     * 区域
     */
    private final String region;

    /**
     * 分隔符
     */
    private final String delimiter;

    /**
     * 是否递归
     */
    private final Boolean recursive;

    /**
     * 是否使用 UrlEncoding
     */
    private final boolean useUrlEncodingType;


    /**
     * 最大关键字数量 1 到 1000
     */
    private final Integer maxKeys;

    /**
     * 前缀
     */
    private final String prefix;

    private final boolean useApiVersion1;

    /**
     * 关键字
     */
    private final String marker;

    /**
     * 额外的请求头
     */
    private final Map<String, String> extraHeaders;


    /**
     * 额外的Query参数
     */
    private final Map<String, String> extraQueryParams;
    ListObjectCmd(String bucketName, String region, String delimiter, Boolean recursive, boolean useUrlEncodingType, Integer maxKeys, String prefix, boolean useApiVersion1, String marker, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.bucketName = bucketName;
        this.region = region;
        this.delimiter = delimiter;
        this.recursive = recursive;
        this.useUrlEncodingType = useUrlEncodingType;
        this.maxKeys = maxKeys;
        this.prefix = prefix;
        this.useApiVersion1 = useApiVersion1;
        this.marker = marker;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }

    public static ListObjectCmdBuilder builder() {
        return new ListObjectCmdBuilder();
    }
    public static class ListObjectCmdBuilder {
        private String bucketName;
        private String region;
        private String delimiter;
        private Boolean recursive = Boolean.TRUE;
        private boolean useUrlEncodingType;
        private Integer maxKeys;
        private String prefix;
        private boolean useApiVersion1 = Boolean.FALSE;
        private String marker;

        /**
         * 额外的请求头
         */
        private final Map<String, String> extraHeaders = new LinkedHashMap<>();

        /**
         * 额外的Query参数
         */
        private final Map<String, String> extraQueryParams = new LinkedHashMap<>();

        ListObjectCmdBuilder() {
        }

        public ListObjectCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public ListObjectCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public ListObjectCmdBuilder delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public ListObjectCmdBuilder recursive(Boolean recursive) {
            this.recursive = recursive;
            return this;
        }

        public ListObjectCmdBuilder useUrlEncodingType(boolean useUrlEncodingType) {
            this.useUrlEncodingType = useUrlEncodingType;
            return this;
        }

        public ListObjectCmdBuilder maxKeys(Integer maxKeys) {
            this.maxKeys = maxKeys;
            return this;
        }

        public ListObjectCmdBuilder prefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public ListObjectCmdBuilder useApiVersion1(boolean useApiVersion1) {
            this.useApiVersion1 = useApiVersion1;
            return this;
        }

        public ListObjectCmdBuilder marker(String marker) {
            this.marker = marker;
            return this;
        }

        public ListObjectCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public ListObjectCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }

        public ListObjectCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public ListObjectCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }
        public ListObjectCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            return new ListObjectCmd(this.bucketName, this.region, this.delimiter, this.recursive, this.useUrlEncodingType, this.maxKeys, this.prefix, this.useApiVersion1, this.marker, this.extraHeaders, this.extraQueryParams);
        }
    }
}
