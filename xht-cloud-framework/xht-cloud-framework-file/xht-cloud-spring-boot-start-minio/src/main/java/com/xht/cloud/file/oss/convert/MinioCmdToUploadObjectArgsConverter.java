package com.xht.cloud.file.oss.convert;

import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.UploadObjectCmd;
import io.micrometer.common.util.StringUtils;
import io.minio.UploadObjectArgs;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToUploadObjectArgsConverter implements OssConverter<UploadObjectCmd, UploadObjectArgs> {
    @SneakyThrows
    @Override
    public UploadObjectArgs convert(@NotNull UploadObjectCmd source) {
        UploadObjectArgs.Builder builder = UploadObjectArgs.builder();
        builder.bucket(source.getBucketName())
                .tags(source.getTags())
                .object(source.getObjectName())
                .filename(source.getFileName())
                .contentType(source.getContentType().getValue());
        if (StringUtils.isNotBlank(source.getRegion())) {
            builder.region(source.getRegion());
        }
        if (MapUtil.isNotEmpty(source.getRequestHeaders())) {
            builder.headers(source.getRequestHeaders());
        }
        if (MapUtil.isNotEmpty(source.getMetadata())) {
            builder.userMetadata(source.getMetadata());
        }
        if (MapUtil.isNotEmpty(source.getExtraHeaders())) {
            builder.extraHeaders(source.getExtraHeaders());
        }
        if (MapUtil.isNotEmpty(source.getExtraQueryParams())) {
            builder.extraQueryParams(source.getExtraQueryParams());
        }
        return builder.build();
    }
}
