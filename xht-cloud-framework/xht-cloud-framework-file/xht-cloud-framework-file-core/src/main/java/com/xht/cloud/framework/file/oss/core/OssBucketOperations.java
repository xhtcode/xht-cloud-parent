package com.xht.cloud.framework.file.oss.core;


import com.xht.cloud.framework.file.oss.dto.BucketDTO;
import com.xht.cloud.framework.file.oss.dto.cmd.CreateBucketCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteBucketCmd;

import java.util.List;

/**
 * 描述 ：存储桶操作类
 *
 * @author : 小糊涂
 **/
public interface OssBucketOperations {

    /**
     * 查询所有存储桶
     *
     * @return Bucket 列表
     */
    List<BucketDTO> listBuckets();

    /**
     * 存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return 是否存在，true 存在，false 不存在
     */
    boolean doesBucketExist(String bucketName);

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     */
    boolean createBucket(String bucketName);

    /**
     * 创建存储桶
     *
     * @param bucketCmd {@link CreateBucketCmd}
     */
    boolean createBucket(CreateBucketCmd bucketCmd);

    /**
     * 删除一个空的存储桶 如果存储桶存在对象不为空时，删除会报错。
     *
     * @param bucketName 存储桶名称
     */
    void deleteBucket(String bucketName);

    /**
     * 删除一个空的存储桶 如果存储桶存在对象不为空时，删除会报错。
     *
     * @param bucketCmd {@link DeleteBucketCmd}
     */
    void deleteBucket(DeleteBucketCmd bucketCmd);

}
