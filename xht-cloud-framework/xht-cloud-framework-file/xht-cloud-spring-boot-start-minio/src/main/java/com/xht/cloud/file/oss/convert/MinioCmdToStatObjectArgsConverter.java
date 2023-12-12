package com.xht.cloud.file.oss.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.GetStatObjectCmd;
import io.micrometer.common.util.StringUtils;
import io.minio.StatObjectArgs;
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
public class MinioCmdToStatObjectArgsConverter implements OssConverter<GetStatObjectCmd, StatObjectArgs> {
    @Override
    public StatObjectArgs convert(@NotNull GetStatObjectCmd source) {
        StatObjectArgs.Builder builder = StatObjectArgs.builder()
                .bucket(source.getBucketName())
                .object(source.getObjectName());
        if (StringUtils.isNotBlank(source.getRegion())) {
            builder.region(source.getRegion());
        }
        if (StringUtils.isNotBlank(source.getVersionId())) {
            builder.versionId(source.getVersionId());
        }
        if (Objects.nonNull(source.getLength()) && source.getLength() > 0) {
            builder.length(source.getLength());
        }
        if (Objects.nonNull(source.getOffset()) && source.getOffset() >= 0) {
            builder.offset(source.getOffset());
        }
        if (!CollectionUtils.isEmpty(source.getMatchETag())) {
            builder.matchETag(CollUtil.join(source.getMatchETag(), ","));
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
