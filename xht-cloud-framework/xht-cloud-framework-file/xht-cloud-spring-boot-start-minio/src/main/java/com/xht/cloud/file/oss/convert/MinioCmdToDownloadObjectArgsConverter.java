package com.xht.cloud.file.oss.convert;

import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.DownloadObjectCmd;
import io.minio.DownloadObjectArgs;
import org.jetbrains.annotations.NotNull;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToDownloadObjectArgsConverter implements OssConverter<DownloadObjectCmd, DownloadObjectArgs> {
    @Override
    public DownloadObjectArgs convert(@NotNull DownloadObjectCmd source) {
        DownloadObjectArgs.Builder builder = DownloadObjectArgs.builder();
        builder.bucket(source.getBucketName());
        builder.object(source.getObjectName());
        builder.filename(source.getFileName());
        builder.overwrite(source.getOverwrite());
        if (StringUtils.hasText(source.getRegion())) {
            builder.region(source.getRegion());
        }
        if (StringUtils.hasText(source.getVersionId())) {
            builder.versionId(source.getVersionId());
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
