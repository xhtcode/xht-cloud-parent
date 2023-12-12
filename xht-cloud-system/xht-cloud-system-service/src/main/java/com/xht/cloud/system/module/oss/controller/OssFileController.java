package com.xht.cloud.system.module.oss.controller;

import com.xht.cloud.file.oss.configuration.MinioProperties;
import com.xht.cloud.file.oss.service.MinioOssTemplate;
import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.file.oss.dto.PutObjectDTO;
import com.xht.cloud.framework.file.oss.dto.cmd.PutObjectCmd;
import com.xht.cloud.framework.security.annotaion.SkipAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@RestController
@RequestMapping("/system/oss")
@RequiredArgsConstructor
public class OssFileController {

    private final MinioOssTemplate minioOssTemplate;

    private final MinioProperties minioProperties;

    @SkipAuthentication(value = false)
    @PostMapping("/upload")
    public R<PutObjectDTO> upload(@RequestParam("file") MultipartFile file) throws IOException {
        PutObjectDTO putObjectDTO = minioOssTemplate.putObject(PutObjectCmd.builder()
                .bucketName(minioProperties.getBucketName())
                .objectName("data" + "/test/" + file.getOriginalFilename())
                .stream(file.getInputStream())
                .build());
        return R.ok("上传成功", putObjectDTO);
    }

}
