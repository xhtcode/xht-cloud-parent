package com.xht.cloud.framework.web.doc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger
     */
    private Boolean enabled = true;

    /**
     * 标题
     */
    private String title = "小糊涂微服务-接口文档";
    /**
     * 描述
     */
    private String description = "小糊涂微服务Swagger版";
    /**
     * 作者
     */
    private String author = "小糊涂";
    /**
     * 版本
     */
    private String version = "plus";
    /**
     * url
     */
    private String url = "www.baidu.com";
    /**
     * email
     */
    private String email = "616326125@qq.com";

}
