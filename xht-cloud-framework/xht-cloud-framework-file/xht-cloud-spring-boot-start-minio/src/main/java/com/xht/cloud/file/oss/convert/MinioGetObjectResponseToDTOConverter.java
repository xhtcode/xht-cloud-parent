package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.file.oss.convert.OssConverter;
import com.xht.cloud.framework.file.oss.dto.GetObjectDTO;
import io.minio.GetObjectResponse;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioGetObjectResponseToDTOConverter implements OssConverter<GetObjectResponse, GetObjectDTO> {
    @Override
    public GetObjectDTO convert(@NotNull GetObjectResponse source) {
        GetObjectDTO dto = new GetObjectDTO();
        dto.setObjectContent(source);
        dto.setBucketName(source.bucket());
        dto.setRegion(source.region());
        dto.setObjectName(source.object());
        Headers headers = source.headers();
        if (Objects.nonNull(headers) && !CollectionUtils.isEmpty(headers.names())) {
            Map<String, Object> header = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            for (String key : headers.names()) {
                header.put(key, headers.get(key));
            }
            dto.setHeader(header);
        }
        return dto;
    }
}
