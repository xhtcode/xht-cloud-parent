package com.xht.cloud.file.oss.convert;

import cn.hutool.core.map.MapUtil;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteObjectsCmd;
import com.xht.cloud.framework.file.oss.exception.OssException;
import io.micrometer.common.util.StringUtils;
import io.minio.RemoveObjectsArgs;
import io.minio.messages.DeleteObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioCmdToRemoveObjectsArgsConvert implements OssConverter<DeleteObjectsCmd, RemoveObjectsArgs> {
    @Override
    public RemoveObjectsArgs convert(@NotNull DeleteObjectsCmd source) {
        RemoveObjectsArgs.Builder builder = RemoveObjectsArgs.builder()
                .bucket(source.getBucketName())
                .bypassGovernanceMode(source.isBypassGovernanceMode());
        List<DeleteObject> deleteObjects = source.getObjects().stream().map(item -> new DeleteObject(item.getObjectName(), item.getVersionId())).toList();
        if (CollectionUtils.isEmpty(deleteObjects)) {
            throw new OssException("deleteObjects is empty! ");
        }
        builder.objects(deleteObjects);
        if (StringUtils.isNotBlank(source.getRegion())) {
            builder.region(source.getRegion());
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
