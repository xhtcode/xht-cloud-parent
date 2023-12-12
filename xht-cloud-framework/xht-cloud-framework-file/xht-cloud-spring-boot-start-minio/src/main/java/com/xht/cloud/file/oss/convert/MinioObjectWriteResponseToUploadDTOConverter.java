package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.UploadObjectDTO;
import io.minio.ObjectWriteResponse;
import org.jetbrains.annotations.NotNull;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioObjectWriteResponseToUploadDTOConverter implements OssConverter<ObjectWriteResponse, UploadObjectDTO> {
    @Override
    public UploadObjectDTO convert(@NotNull ObjectWriteResponse response) {
        UploadObjectDTO dto = new UploadObjectDTO();
        dto.setEtag(response.etag());
        dto.setVersionId(response.versionId());
        dto.setBucketName(response.bucket());
        dto.setRegion(response.region());
        dto.setObjectName(response.object());
        return dto;
    }
}
