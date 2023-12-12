package com.xht.cloud.file.oss.convert;

import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.ListObjectCmd;
import io.micrometer.common.util.StringUtils;
import io.minio.ListObjectsArgs;
import org.jetbrains.annotations.NotNull;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToListObjectsArgsConverter implements OssConverter<ListObjectCmd, ListObjectsArgs> {

    @Override
    public ListObjectsArgs convert(@NotNull ListObjectCmd source) {
        ListObjectsArgs.Builder builder = ListObjectsArgs.builder();
        builder.bucket(source.getBucketName());
        if (StringUtils.isNotBlank(source.getRegion())) {
            builder.region(source.getRegion());
        }
        builder.delimiter(source.getDelimiter());
        builder.useUrlEncodingType(source.isUseUrlEncodingType());
        builder.maxKeys(source.getMaxKeys());
        builder.prefix(source.getPrefix());
        builder.recursive(source.getRecursive());
        builder.useApiVersion1(source.isUseApiVersion1());
        if (StringUtils.isNotBlank(source.getMarker())) {
            builder.keyMarker(source.getMarker());
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
