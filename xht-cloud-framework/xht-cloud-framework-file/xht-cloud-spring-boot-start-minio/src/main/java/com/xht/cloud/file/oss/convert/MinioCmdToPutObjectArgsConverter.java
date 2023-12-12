package com.xht.cloud.file.oss.convert;

import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.PutObjectCmd;
import io.micrometer.common.util.StringUtils;
import io.minio.PutObjectArgs;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToPutObjectArgsConverter implements OssConverter<PutObjectCmd, PutObjectArgs> {
    @Override
    public PutObjectArgs convert(@NotNull PutObjectCmd source) {
        PutObjectArgs.Builder builder = PutObjectArgs.builder();
        builder.bucket(source.getBucketName())
                .object(source.getObjectName())
                .stream(source.getStream(), source.getObjectSize(), source.getPartSize())
                .tags(source.getTags());
        if (StringUtils.isNotBlank(source.getRegion())) {
            builder.region(source.getRegion());
        }
        if (Objects.nonNull(source.getContentType())) {
            builder.contentType(source.getContentType().getValue());
        }
        if (MapUtil.isNotEmpty(source.getMetadata())) {
            builder.userMetadata(source.getMetadata());
        }
        if (MapUtil.isNotEmpty(source.getRequestHeaders())) {
            builder.headers(source.getRequestHeaders());
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
