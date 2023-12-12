package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.PutObjectDTO;
import io.minio.ObjectWriteResponse;
import org.jetbrains.annotations.NotNull;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioObjectWriteResponseToDTOConverter implements OssConverter<ObjectWriteResponse, PutObjectDTO> {
    @Override
    public PutObjectDTO convert(@NotNull ObjectWriteResponse response) {
        PutObjectDTO dto = new PutObjectDTO();
        dto.setBucketName(response.bucket());
        dto.setRegion(response.region());
        dto.setObjectName(response.object());
        dto.setEtag(response.etag());
        dto.setVersionId(response.versionId());
        return dto;
    }
}
