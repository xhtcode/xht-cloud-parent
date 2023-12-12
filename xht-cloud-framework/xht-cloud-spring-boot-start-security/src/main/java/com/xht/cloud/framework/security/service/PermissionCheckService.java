package com.xht.cloud.framework.security.service;

import cn.hutool.core.util.ArrayUtil;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Set;

/**
 * 描述 ： 权限校验
 *
 * @author : 小糊涂
 **/
public class PermissionCheckService {

    /**
     * 判断接口是否有任意xxx，xxx权限
     *
     * @param roleCode 权限
     * @return {boolean}
     */
    public boolean hasRoleCode(String... roleCode) {
        if (SecurityContextUtil.isLogin() && !ArrayUtil.isEmpty(roleCode)) {
            CustomUserDetails authentication = SecurityContextUtil.getUser().orElse(null);
            assert authentication != null;
            Set<String> authorities = authentication.getRoleCode();
            return authorities.stream().filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(roleCode, x));
        }
        return false;
    }

    /**
     * 判断接口是否有任意xxx，xxx权限
     *
     * @param permissions 权限
     * @return {boolean}
     */
    public boolean hasAnyAuthority(String... permissions) {
        if (SecurityContextUtil.isLogin()) {
            if (SecurityContextUtil.isClientCredentials() || ArrayUtil.isEmpty(permissions)) {
                return false;
            }
            if (SecurityContextUtil.isAdmin()) {
                return true;
            }
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            return authorities.stream().map(GrantedAuthority::getAuthority).filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(permissions, x));
        }
        return false;
    }
}
