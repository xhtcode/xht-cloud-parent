package com.xht.cloud.file.oss.convert;

import com.xht.cloud.framework.file.oss.dto.ListObjectsDTO;
import com.xht.cloud.framework.file.oss.dto.ObjectsDTO;
import com.xht.cloud.framework.file.oss.dto.cmd.ListObjectCmd;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class MinioListResultItemToDTOConvert implements Converter<Iterable<Result<Item>>, ListObjectsDTO> {
    private final String bucketName;

    private final String prefix;
    private final ListObjectCmd objectCmd;

    public MinioListResultItemToDTOConvert(ListObjectCmd objectCmd) {
        this.objectCmd = objectCmd;
        this.bucketName = objectCmd.getBucketName();
        this.prefix = objectCmd.getPrefix();
    }

    @SneakyThrows
    @Override
    public ListObjectsDTO convert(@NotNull Iterable<Result<Item>> source) {
        ResultItemToDomainConverter resultItemToDomainConverter = new ResultItemToDomainConverter(this.bucketName);
        List<ObjectsDTO> objectDomains = new ArrayList<>();
        for (Result<Item> itemResult : source) {
            ObjectsDTO convert = resultItemToDomainConverter.convert(itemResult);
            objectDomains.add(convert);
        }

        return getListObjectsDTO(objectDomains);
    }

    @NotNull
    private ListObjectsDTO getListObjectsDTO(List<ObjectsDTO> objectDomains) {
        ListObjectsDTO domain = new ListObjectsDTO();
        domain.setBucketName(this.bucketName);
        domain.setPrefix(this.prefix);
        if (Objects.nonNull(objectCmd)) {
            domain.setMarker(objectCmd.getMarker());
            domain.setDelimiter(objectCmd.getDelimiter());
            domain.setMaxKeys(objectCmd.getMaxKeys());
            domain.setEncodingType("");
            domain.setBucketName(objectCmd.getBucketName());
        }
        domain.setSummaries(objectDomains);
        return domain;
    }
}
