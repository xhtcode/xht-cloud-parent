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
 * 描述 ：删除存储桶请求参数DTO
 *
 * @author : 小糊涂
 */
@Getter
public class DeleteBucketCmd implements Serializable {

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
    DeleteBucketCmd(String bucketName, String region) {
        this.bucketName = bucketName;
        this.region = region;
    }

    /**
     * @return 创建一个构造器
     */
    public static DeleteBucketCmdBuilder builder() {
        return new DeleteBucketCmdBuilder();
    }

    public static class DeleteBucketCmdBuilder implements Builder<DeleteBucketCmd> {

        /**
         * 存储桶名称
         */
        private String bucketName;

        /**
         * 区域
         */
        private String region;

        private DeleteBucketCmdBuilder() {
        }


        public DeleteBucketCmdBuilder bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public DeleteBucketCmdBuilder region(String region) {
            this.region = region;
            return this;
        }

        /**
         * 构建方法
         *
         * @return 被构建的对象 {@link DeleteBucketCmd}
         */
        @Override
        public DeleteBucketCmd build() {
            Assert.isFalse(!StringUtils.hasText(this.bucketName), () -> new OssException(OssErrorStatusCode.BUCKET_NAME_EMPTY));
            return new DeleteBucketCmd(bucketName, region);
        }
    }


}
