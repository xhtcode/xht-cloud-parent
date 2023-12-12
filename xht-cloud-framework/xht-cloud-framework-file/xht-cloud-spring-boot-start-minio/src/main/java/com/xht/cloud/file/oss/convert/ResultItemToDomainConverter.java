package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.core.support.ObjectUtils;
import com.xht.cloud.framework.file.oss.dto.ObjectsDTO;
import io.minio.Result;
import io.minio.messages.Item;
import io.minio.messages.Owner;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class ResultItemToDomainConverter implements Converter<Result<Item>, ObjectsDTO> {
    private final String bucketName;

    public ResultItemToDomainConverter(String bucketName) {
        this.bucketName = bucketName;
    }

    @SneakyThrows
    @Override
    public ObjectsDTO convert(@NotNull Result<Item> source) {
        Item item = source.get();
        ObjectsDTO objectDomain = new ObjectsDTO();
        objectDomain.setBucketName(bucketName);
        objectDomain.setObjectName(item.objectName());
        objectDomain.setIsDir(item.isDir());
        if (!item.isDir()) {
            objectDomain.setETag(item.etag());
            objectDomain.setLastModified(LocalDateTime.from(item.lastModified()));
            if (ObjectUtils.isNotEmpty(item.owner())) {
                Owner owner = item.owner();
                objectDomain.setId(owner.id());
                objectDomain.setDisplayName(owner.displayName());
            }
            objectDomain.setSize(item.size());
            objectDomain.setStorageClass(item.storageClass());

        }
        return objectDomain;
    }
}
