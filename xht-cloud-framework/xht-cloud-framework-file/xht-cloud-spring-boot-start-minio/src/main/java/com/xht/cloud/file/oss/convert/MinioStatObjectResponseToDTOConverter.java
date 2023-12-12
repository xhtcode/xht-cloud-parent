package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.file.oss.dto.StatObjectDTO;
import io.minio.StatObjectResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioStatObjectResponseToDTOConverter implements Converter<StatObjectResponse, StatObjectDTO> {

    @Override
    public StatObjectDTO convert(@NotNull StatObjectResponse source) {
        StatObjectDTO dto = new StatObjectDTO();
        dto.setUserMetadata(source.userMetadata());
        dto.setContentLength(source.size());
        dto.setContentType(source.contentType());
        dto.setLastModified(LocalDateTime.from(source.lastModified()));
        dto.setEtag(source.etag());
        dto.setVersionId(source.versionId());
        dto.setBucketName(source.bucket());
        dto.setRegion(source.region());
        dto.setObjectName(source.object());
        return dto;
    }
}
