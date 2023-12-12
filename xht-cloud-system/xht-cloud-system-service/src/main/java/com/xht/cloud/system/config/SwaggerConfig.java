package com.xht.cloud.system.config;

import com.xht.cloud.framework.web.tool.GroupedOpenApiTool;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Configuration
public class SwaggerConfig {

    /**
     * 文档分组 - 系统管理
     */
    @Bean
    public GroupedOpenApi system() {
        return GroupedOpenApiTool.build("系统管理", "/sys/**");
    }

    /**
     * 文档分组 - oauth2
     */
    @Bean("oauthApi")
    public GroupedOpenApi oauth2() {
        return GroupedOpenApiTool.build("oauth2", "/oauth2/**");
    }
}
