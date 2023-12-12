package com.xht.cloud.framework.file.oss.dto.cmd;

import com.xht.cloud.framework.core.builder.Builder;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.enums.OssErrorStatusCode;
import com.xht.cloud.framework.file.oss.exception.OssException;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述 ：创建存储桶参数
 *
 * @author : 小糊涂
 */
@Getter
public class CreateBucketCmd implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 存储桶名称
     */
    private final String bucketName;

    /**
     * 区域
     */
    private final String region;

    /**
     * @param bucketName 存储桶名称
     * @param region     区域
     */
    CreateBucketCmd(String bucketName, String region) {
        this.bucketName = bucketName;
        this.region = region;
    }

    /**
     * @return 创建一个构造器
     */
    public static CreateBucketCmdBuilder builder() {
        return new CreateBucketCmdBuilder();
    }

    public static class CreateBucketCmdBuilder implements Builder<CreateBucketCmd> {

        /**
         * 存储桶名称
         */
        private String bucketName;

        /**
         * 区域
         */
        private String region;

        CreateBucketCmdBuilder() {
        }

        public CreateBucketCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public CreateBucketCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        /**
         * 构建方法
         *
         * @return 被构建的对象
         */
        @Override
        public CreateBucketCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            return new CreateBucketCmd(this.bucketName, this.region);
        }
    }

}
