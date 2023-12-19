package com.xht.cloud.framework.safety.xss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 ：xss 配置
 *
 * @author : 小糊涂
 **/
@Data
@ConfigurationProperties(prefix = PKG_CONSTANT.configPrefix)
public class XSSProperties {

    /**
     * 是否开启，默认为 true
     */
    private boolean enable = true;

    /**
     * 需要排除的 URL，默认为空
     */
    private List<String> excludeUrls = new ArrayList<>();
}
