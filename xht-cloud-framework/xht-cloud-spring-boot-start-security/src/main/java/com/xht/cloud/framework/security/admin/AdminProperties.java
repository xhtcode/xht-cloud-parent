package com.xht.cloud.framework.security.admin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述 ：声名 admin
 *
 * @author : 小糊涂
 **/
@Data
@ConfigurationProperties("xht.security.oauth2.admin")
public class AdminProperties {

    /**
     * 用户账号
     */
    private String include = "admin";

    /**
     * 用户账号
     */
    private List<String> includes = new ArrayList<>();

    /**
     * 角色id
     */
    private Set<String> roleCode = new HashSet<>();
}
