package com.xht.cloud.framework.security.core.userdetails;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述 ：Spring Security 用户信息
 *
 * @author : 小糊涂
 **/
@Getter
public class CustomUserDetails extends User implements OAuth2AuthenticatedPrincipal {

    /**
     * 扩展属性，方便存放oauth 上下文相关信息
     */
    private final Map<String, Object> attributes = new HashMap<>();

    /**
     * 用户id
     */
    @Getter
    @Setter
    private String userId;

    /**
     * 用户姓名
     */
    private final String username;

    /**
     * 用户密码
     */
    private final String password;
    @Getter
    @Setter
    private String passWordSalt;

    @Getter
    @Setter
    private boolean enabled;

    @Getter
    @Setter
    private Set<String> roleCode;

    /**
     * 账号类型，0 系统管理员 1 用户 2 商家
     */
    @Getter
    @Setter
    private String userType;

    /**
     * 部门id
     */
    @Getter
    @Setter
    private String deptId;

    /**
     * 数据权限级别
     */
    @Getter
    @Setter
    private Integer dataScope;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
    }

    public CustomUserDetails(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get the OAuth 2.0 token attributes
     *
     * @return the OAuth 2.0 token attributes
     */
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public CustomUserDetails put(final String key, final String value) {
        this.attributes.put(key, value);
        return this;
    }

    /**
     * Returns the name of the authenticated <code>Principal</code>. Never
     * <code>null</code>.
     *
     * @return the name of the authenticated <code>Principal</code>
     */
    @Override
    public String getName() {
        return this.username;
    }
}
