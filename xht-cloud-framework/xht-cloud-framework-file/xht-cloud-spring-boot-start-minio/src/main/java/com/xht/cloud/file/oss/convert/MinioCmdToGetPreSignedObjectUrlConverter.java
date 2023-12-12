package com.xht.cloud.file.oss.convert;

import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.GetPreSignedObjectUrlCmd;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToGetPreSignedObjectUrlConverter implements OssConverter<GetPreSignedObjectUrlCmd, GetPresignedObjectUrlArgs> {
    @Override
    public GetPresignedObjectUrlArgs convert(@NotNull GetPreSignedObjectUrlCmd source) {
        GetPresignedObjectUrlArgs.Builder builder = GetPresignedObjectUrlArgs.builder()
                .bucket(source.getBucketName())
                .object(source.getObjectName())
                .method(Method.valueOf(source.getMethod().name()))
                .versionId(source.getVersionId());
        if (StringUtils.hasText(source.getRegion())) {
            builder.region(source.getRegion());
        }
        if (Objects.nonNull(source.getExpiration())) {
            builder.expiry(Math.toIntExact(source.getExpiration().toSeconds()));
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
