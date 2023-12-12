package com.xht.cloud.file.oss.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.GetObjectCmd;
import io.minio.GetObjectArgs;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToGetObjectArgsConverter implements OssConverter<GetObjectCmd, GetObjectArgs> {
    @Override
    public GetObjectArgs convert(@NotNull GetObjectCmd source) {
        GetObjectArgs.Builder builder = GetObjectArgs.builder();
        builder.bucket(source.getBucketName());
        builder.object(source.getObjectName());
        if (StringUtils.hasText(source.getRegion())) {
            builder.region(source.getRegion());
        }
        if (StringUtils.hasText(source.getVersionId())) {
            builder.versionId(source.getVersionId());
        }
        if (Objects.nonNull(source.getLength()) && source.getLength() >= 0) {
            builder.length(source.getLength());
        }
        if (Objects.nonNull(source.getOffset()) && source.getOffset() >= 0) {
            builder.offset(source.getOffset());
        }
        if (!CollectionUtils.isEmpty(source.getMatchETag())) {
            builder.matchETag(CollUtil.join(source.getMatchETag(),","));
        }
        if (!CollectionUtils.isEmpty(source.getNotMatchEtag())) {
            builder.notMatchETag(CollUtil.join(source.getNotMatchEtag(), ","));
        }
        if (Objects.nonNull(source.getModifiedSince())) {
            builder.modifiedSince(ZonedDateTime.of(source.getModifiedSince(), ZoneId.of("Asia/Shanghai")));
        }
        if (Objects.nonNull(source.getUnmodifiedSince())) {
            builder.unmodifiedSince(ZonedDateTime.of(source.getUnmodifiedSince(), ZoneId.of("Asia/Shanghai")));
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
