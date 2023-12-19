package com.xht.cloud.framework.safety.limit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@ConfigurationProperties(prefix = PKG_CONSTANT.configPrefix)
public class RequestLimitProperties {

    /**
     * redis 中key值的前缀
     */
    private final String prefix = "xht:http:limit";
}
