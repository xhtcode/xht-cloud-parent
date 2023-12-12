package com.xht.cloud.framework.security.definition;

import com.xht.cloud.framework.security.constant.SecurityConstant;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * 描述 ：自定义 Grant Type类型
 *
 * @author : 小糊涂
 **/
public interface CustomGrantType {

    /**
     * 密码模式
     */
    AuthorizationGrantType PASSWORD = new AuthorizationGrantType(SecurityConstant.PASSWORD_GRANT_TYPE);
}
