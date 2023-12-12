package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.core.builder.Builder;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public class DownloadObjectCmd implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 存储桶名称
     */
    private final String bucketName;

    /**
     * 对象名称
     */
    private final String objectName;

    /**
     * 具体保存的文件名，包括路径
     */
    private final String fileName;

    /**
     * 是否覆盖
     */
    private final Boolean overwrite;

    /**
     * 区域
     */
    private final String region;

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

    public static DownloadObjectCmdBuilder builder() {
        return new DownloadObjectCmdBuilder();
    }

    /**
     * @param bucketName       存储桶名称
     * @param objectName       对象名称
     * @param fileName         具体保存的文件名，包括路径
     * @param overwrite        是否覆盖
     * @param region           区域
     * @param versionId        版本ID
     * @param extraHeaders     额外的请求头
     * @param extraQueryParams 额外的Query参数
     */
    DownloadObjectCmd(String bucketName, String objectName, String fileName, Boolean overwrite, String region, String versionId, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.bucketName = bucketName;
        this.objectName = objectName;
        this.fileName = fileName;
        this.overwrite = overwrite;
        this.region = region;
        this.versionId = versionId;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }

    public static class DownloadObjectCmdBuilder implements Builder<DownloadObjectCmd> {

        /**
         * 存储桶名称
         */
        private String bucketName;

        /**
         * 对象名称
         */
        private String objectName;

        /**
         * 具体保存的文件名，包括路径
         */
        private String fileName;

        /**
         * 是否覆盖
         */
        private Boolean overwrite = Boolean.TRUE;

        /**
         * 区域
         */
        private String region;

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

        DownloadObjectCmdBuilder() {
        }

        public DownloadObjectCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }


        public DownloadObjectCmdBuilder objectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public DownloadObjectCmdBuilder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public DownloadObjectCmdBuilder overwrite(Boolean overwrite) {
            this.overwrite = overwrite;
            return this;
        }

        public DownloadObjectCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public DownloadObjectCmdBuilder versionId(String versionId) {
            this.versionId = versionId;
            return this;
        }

        public DownloadObjectCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public DownloadObjectCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }

        public DownloadObjectCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public DownloadObjectCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }

        public DownloadObjectCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.objectName), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.fileName), () -> new OssException(OssErrorStatusCode.SAVE_FILE_PATH_ERROR));
            return new DownloadObjectCmd(this.bucketName, this.objectName, this.fileName, this.overwrite, this.region, this.versionId, this.extraHeaders, this.extraQueryParams);
        }
    }
}
