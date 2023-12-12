package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.file.oss.dto.cmd.CreateBucketCmd;
import io.minio.MakeBucketArgs;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

/**
 * 描述 ：创建存储桶转换器
 *
 * @author : 小糊涂
 **/
public class MinioCmdToMakeBucketArgsConverter implements Converter<CreateBucketCmd, MakeBucketArgs> {

    @Override
    public MakeBucketArgs convert(@NotNull CreateBucketCmd source) {
        // @formatter:off
        return MakeBucketArgs.builder()
                .bucket(source.getBucketName())
                .region(source.getRegion())
                .build();
        // @formatter:on
    }
}
