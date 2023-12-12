package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.core.support.ObjectUtils;
import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.DeleteObjectsDTO;
import io.minio.Result;
import io.minio.messages.DeleteError;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioDeleteResultItemToDTOConvert implements OssConverter<Result<DeleteError>,DeleteObjectsDTO> {
    @SneakyThrows
    @Override
    public DeleteObjectsDTO convert(@NotNull Result<DeleteError> source) {
        DeleteError deleteError = source.get();
        DeleteObjectsDTO dto = new DeleteObjectsDTO();
        if (ObjectUtils.isNotEmpty(deleteError)) {
            dto.setCode(deleteError.code());
            dto.setMessage(deleteError.message());
            dto.setBucketName(deleteError.bucketName());
            dto.setObjectName(deleteError.objectName());
            dto.setResource(deleteError.resource());
            dto.setRequestId(deleteError.requestId());
            dto.setHostId(deleteError.hostId());
        }
        return dto;
    }
}
