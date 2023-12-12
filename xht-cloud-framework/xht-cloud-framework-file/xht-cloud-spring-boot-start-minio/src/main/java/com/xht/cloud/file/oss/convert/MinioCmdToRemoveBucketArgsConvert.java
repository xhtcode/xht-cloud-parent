package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.file.oss.dto.cmd.DeleteBucketCmd;
import io.minio.RemoveBucketArgs;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToRemoveBucketArgsConvert implements Converter<DeleteBucketCmd, RemoveBucketArgs> {
    @Override
    public RemoveBucketArgs convert(@NotNull DeleteBucketCmd source) {
        // @formatter:off
        return RemoveBucketArgs.builder()
                .bucket(source.getBucketName())
                .region(source.getRegion())
                .build();
        // @formatter:on
    }

}
