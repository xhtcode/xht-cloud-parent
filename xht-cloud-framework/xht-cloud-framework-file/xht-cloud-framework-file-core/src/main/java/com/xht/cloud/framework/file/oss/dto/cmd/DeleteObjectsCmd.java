package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.core.builder.Builder;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

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
public class DeleteObjectsCmd {
    /**
     * bucketName – 存储桶名称
     */
    private final String bucketName;

    /**
     * objects – 待删除对象
     */
    private final List<DeleteObjectsItemCmd> objects;

    /**
     * region – 区域
     */
    private final String region;


    /**
     * 使用 Governance 模式
     */
    private final boolean bypassGovernanceMode;

    /**
     * 额外的请求头
     */
    private final Map<String, String> extraHeaders;


    /**
     * 额外的Query参数
     */
    private final Map<String, String> extraQueryParams;


    DeleteObjectsCmd(String bucketName, String region, List<DeleteObjectsItemCmd> objects, boolean bypassGovernanceMode, Map<String, String> extraHeaders, Map<String, String> extraQueryParams) {
        this.bucketName = bucketName;
        this.region = region;
        this.objects = objects;
        this.bypassGovernanceMode = bypassGovernanceMode;
        this.extraHeaders = extraHeaders;
        this.extraQueryParams = extraQueryParams;
    }


    /**
     * @return 创建一个构造器
     */
    public static DeleteObjectsCmdBuilder builder() {
        return new DeleteObjectsCmdBuilder();
    }

    public static class DeleteObjectsCmdBuilder implements Builder<DeleteObjectsCmd> {

        /**
         * bucketName – 存储桶名称
         */
        private String bucketName;

        /**
         * objects – 待删除对象
         */
        private final List<DeleteObjectsItemCmd> objects = new ArrayList<>();

        /**
         * region – 区域
         */
        private String region;

        /**
         * 使用 Governance 模式
         */
        private boolean bypassGovernanceMode;

        /**
         * 额外的请求头
         */
        private final Map<String, String> extraHeaders = new LinkedHashMap<>();


        /**
         * 额外的Query参数
         */
        private final Map<String, String> extraQueryParams = new LinkedHashMap<>();


        private DeleteObjectsCmdBuilder() {
        }

        public DeleteObjectsCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public DeleteObjectsCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        public DeleteObjectsCmdBuilder objects(List<DeleteObjectsItemCmd> objects) {
            this.objects.addAll(objects);
            return this;
        }

        /**
         * @param objectName 对象名称
         * @param versionId  对象版本ID
         */
        public DeleteObjectsCmdBuilder objects(String objectName, String versionId) {
            this.objects.add(new DeleteObjectsItemCmd(objectName, versionId));
            return this;
        }

        public DeleteObjectsCmdBuilder bypassGovernanceMode(boolean bypassGovernanceMode) {
            this.bypassGovernanceMode = bypassGovernanceMode;
            return this;
        }

        public DeleteObjectsCmdBuilder extraHeaders(Map<String, String> extraHeaders) {
            this.extraHeaders.putAll(extraHeaders);
            return this;
        }

        public DeleteObjectsCmdBuilder extraQueryParams(Map<String, String> extraQueryParams) {
            this.extraQueryParams.putAll(extraQueryParams);
            return this;
        }

        public DeleteObjectsCmdBuilder extraHeaders(String key, String value) {
            this.extraHeaders.put(key, value);
            return this;
        }

        public DeleteObjectsCmdBuilder extraQueryParams(String key, String value) {
            this.extraQueryParams.put(key, value);
            return this;
        }

        /**
         * 构建方法
         *
         * @return 被构建的对象 {@link DeleteObjectsCmd}
         */
        @Override
        public DeleteObjectsCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            Assert.isFalse(CollectionUtils.isEmpty(this.objects), () -> new OssException(OssErrorStatusCode.OBJECT_NAME_EMPTY));
            return new DeleteObjectsCmd(this.bucketName, this.region, this.objects, this.bypassGovernanceMode, this.extraHeaders, this.extraQueryParams);
        }
    }



}
