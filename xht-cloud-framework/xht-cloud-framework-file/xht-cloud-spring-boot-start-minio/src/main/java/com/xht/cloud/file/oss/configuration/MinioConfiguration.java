package com.xht.cloud.file.oss.configuration;

import com.xht.cloud.file.oss.repository.MinioFileRepository;
import com.xht.cloud.file.oss.service.MinioOssTemplate;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Configuration
@EnableConfigurationProperties({MinioProperties.class})
@RequiredArgsConstructor
public class MinioConfiguration {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        // @formatter:off
        return MinioClient.builder()
                .endpoint(minioProperties.getHost())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        // @formatter:on
    }

    @Bean
    @ConditionalOnBean(MinioClient.class)
    public MinioFileRepository getMinioFileRepository() {
        return new MinioFileRepository(minioClient());
    }

    @Bean
    @ConditionalOnBean(MinioFileRepository.class)
    public MinioOssTemplate getMinioTemplate() {
        return new MinioOssTemplate(getMinioFileRepository());
    }

}
