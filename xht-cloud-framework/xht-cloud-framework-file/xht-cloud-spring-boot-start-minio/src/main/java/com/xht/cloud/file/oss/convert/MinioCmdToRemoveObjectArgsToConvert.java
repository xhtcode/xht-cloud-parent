package com.xht.cloud.file.oss.convert;

import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.core.support.ObjectUtils;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteObjectCmd;
import io.micrometer.common.util.StringUtils;
import io.minio.RemoveObjectArgs;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToRemoveObjectArgsToConvert implements Converter<DeleteObjectCmd, RemoveObjectArgs> {
    @Override
    public RemoveObjectArgs convert(@NotNull DeleteObjectCmd source) {
        RemoveObjectArgs.Builder builder = RemoveObjectArgs.builder()
                .bucket(source.getBucketName())
                .object(source.getObjectName())
                .versionId(source.getVersionId());
        if (StringUtils.isNotBlank(source.getRegion())) {
            builder.region(source.getRegion());
        }
        if (ObjectUtils.isNotEmpty(source.getBypassGovernanceMode())) {
            builder.bypassGovernanceMode(source.getBypassGovernanceMode());
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
