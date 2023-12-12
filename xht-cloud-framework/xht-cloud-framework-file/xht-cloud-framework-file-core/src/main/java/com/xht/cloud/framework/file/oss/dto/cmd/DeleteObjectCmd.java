package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.core.builder.Builder;
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
public class DeleteObjectCmd {

    /**
     * 存储桶名称
     */
    private final String bucketName;

    /**
     * 对象名
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
     * 使用治理模式进行删除
     * io.minio.MinioClient 专用参数
     */
    private final Boolean bypassGovernanceMode;

    /**
     * 额外的请求头
     */
    private final Map<String, String> extraHeaders;


    /**
     * 额外的Query参数
     */
    private final Map<String, String> extraQueryParams;

    DeleteObjectCmd(String bucketName, String region, String objectName, String versionId, Boolean bypassGovernanceMode, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.bucketName = bucketName;
        this.region = region;
        this.objectName = objectName;
        this.versionId = versionId;
        this.bypassGovernanceMode = bypassGovernanceMode;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }

    /**
     * @return 创建一个构造器
     */
    public static DeleteObjectCmdBuilder builder() {
        return new DeleteObjectCmdBuilder();
    }

    public static class DeleteObjectCmdBuilder implements Builder<DeleteObjectCmd> {

        /**
         * 存储桶名称
         */
        private String bucketName;

        /**
         * 对象名
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
         * 使用治理模式进行删除
         * io.minio.MinioClient 专用参数
         */
        private Boolean bypassGovernanceMode;

        /**
         * 额外的请求头
         */
        private final Map<String, String> extraHeaders = new LinkedHashMap<>();

        /**
         * 额外的Query参数
         */
        private final Map<String, String> extraQueryParams = new LinkedHashMap<>();

        private DeleteObjectCmdBuilder() {
        }


        public DeleteObjectCmd.DeleteObjectCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }


        public DeleteObjectCmd.DeleteObjectCmdBuilder objectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public DeleteObjectCmd.DeleteObjectCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public DeleteObjectCmd.DeleteObjectCmdBuilder versionId(String versionId) {
            this.versionId = versionId;
            return this;
        }

        public DeleteObjectCmd.DeleteObjectCmdBuilder bypassGovernanceMode(Boolean bypassGovernanceMode) {
            this.bypassGovernanceMode = bypassGovernanceMode;
            return this;
        }

        public DeleteObjectCmd.DeleteObjectCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public DeleteObjectCmd.DeleteObjectCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }

        public DeleteObjectCmd.DeleteObjectCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public DeleteObjectCmd.DeleteObjectCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }

        /**
         * 构建方法
         *
         * @return 被构建的对象 {@link DeleteObjectCmd}
         */
        @Override
        public DeleteObjectCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            Assert.isFalse(!StringUtils.hasText(this.objectName), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
            return new DeleteObjectCmd(this.bucketName, this.region, this.objectName, this.versionId, this.bypassGovernanceMode, this.extraHeaders, this.extraQueryParams);
        }
    }
}
