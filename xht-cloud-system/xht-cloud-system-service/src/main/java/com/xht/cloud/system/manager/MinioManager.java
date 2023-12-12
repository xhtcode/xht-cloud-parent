package com.xht.cloud.system.manager;

import cn.hutool.core.util.IdUtil;
import com.xht.cloud.file.oss.configuration.MinioProperties;
import com.xht.cloud.file.oss.service.MinioOssTemplate;
import com.xht.cloud.framework.file.core.enums.ContentType;
import com.xht.cloud.framework.file.oss.dto.PutObjectDTO;
import com.xht.cloud.framework.file.oss.dto.cmd.GetPreSignedObjectUrlCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.PutObjectCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class MinioManager {

    private final MinioOssTemplate minioOssTemplate;

    private final MinioProperties minioProperties;

    /**
     * 修改当前登录用户头像
     *
     * @param userName 账号
     * @param content  头像数据
     * @return 头像地址
     */
    public String uploadUserAvatar(String userName, byte[] content) {
        // @formatter:off
        ContentType contentType = ContentType.build(content);
        PutObjectCmd build = PutObjectCmd.builder()
                .bucketName(minioProperties.getBucketName())
                .objectName(String.format("data/user/avatar/%s.%s",IdUtil.getSnowflakeNextId(),contentType.getSuffix()))
                .stream(new ByteArrayInputStream(content))
                .metadata("userName", userName)
                .contentType(contentType)
                .extraHeaders("userName", userName)
                .build();
        PutObjectDTO putObjectDTO = minioOssTemplate.putObject(build);
        GetPreSignedObjectUrlCmd signedObjectUrlCmd = GetPreSignedObjectUrlCmd.builder()
                .bucketName(putObjectDTO.getBucketName())
                .objectName(putObjectDTO.getObjectName())
                .method(HttpMethod.GET)
                .build();
        // @formatter:on
        return minioOssTemplate.getPreSignedObjectUrl(signedObjectUrlCmd);
    }

}
