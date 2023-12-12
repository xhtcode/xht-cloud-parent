package com.xht.cloud.framework.security.support;

import cn.hutool.extra.spring.SpringUtil;
import com.xht.cloud.framework.security.admin.AdminProperties;
import com.xht.cloud.framework.security.constant.SecurityConstant;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 描述 ：springSecurity上下文对象
 *
 * @author : 小糊涂
 **/
public final class SecurityContextUtil {


    /**
     * 获取Authentication
     */
    public static Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    /**
     *  判断用户是否登录
     * @return true 登录 false未登录
     */
    public static boolean isLogin() {
        Authentication authentication = getAuthentication().orElse(null);
        return Objects.nonNull(authentication) && authentication.isAuthenticated();
    }


    /**
     * 获取当前的登录账号
     *
     * @return 当前登录账号
     */
    public static String getUserName() {
        Authentication authentication = getAuthentication().orElse(null);
        if (Objects.nonNull(authentication)) {
            return authentication.getName();
        }
        return SecurityConstant.UNKNOWN_USER_NAME;
    }

    /**
     * 获取用户
     */
    public static Optional<CustomUserDetails> getUser() {
        if (isLogin()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails customUserDetails) {
                return Optional.of(customUserDetails);
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    /**
     * 检测当前token是否是客户端认证
     * @return  true 客户端认证
     */
    public static boolean isClientCredentials() {
        Authentication authentication = getAuthentication().orElse(null);
        if (Objects.isNull(authentication)) {
            return false;
        }
        return authentication.getPrincipal() instanceof OAuth2IntrospectionAuthenticatedPrincipal;
    }


    /**
     * @return 获取当前用户的数据权限
     */
    public static Integer getDataScope() {
        CustomUserDetails user = getUser().orElseThrow(() -> new RuntimeException("暂无权限查看，请联系管理员重新分配部门级数据权限！"));
        return user.getDataScope();
    }

    /**
     * 判断当前登录用户是不是管理员
     *
     * @return {@link Boolean} true 是 false不是
     */
    public static boolean isAdmin() {
        CustomUserDetails user = getUser().orElse(null);
        if (Objects.isNull(user)) {
            return Boolean.FALSE;
        }
        AdminProperties adminProperties = SpringUtil.getBean(AdminProperties.class);
        String include = adminProperties.getInclude();
        List<String> includes = adminProperties.getIncludes();
        Set<String> roleCode = adminProperties.getRoleCode();
        String usernameLogin = user.getUsername();
        List<String> roleCodeLogin = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Objects.equals(include, usernameLogin) || CollectionUtils.contains(includes.iterator(), usernameLogin) || CollectionUtils.containsAny(roleCode, roleCodeLogin);
    }

    /**
     * 判断当前用户是不是管理员
     *
     * @return {@link Boolean} true 是 false不是
     */
    public static Optional<Boolean> isAdmin(String userName) {
        AdminProperties adminProperties = SpringUtil.getBean(AdminProperties.class);
        String include = adminProperties.getInclude();
        List<String> includes = adminProperties.getIncludes();
        return Objects.equals(include, userName) || CollectionUtils.contains(includes.listIterator(), userName) ? Optional.of(true) : Optional.empty();
    }

    /**
     * 判断角色用户是不是管理员
     *
     * @return {@link Boolean} true 是 false不是
     */
    public static Optional<Boolean> isAdminRole(String roleCode) {
        AdminProperties adminProperties = SpringUtil.getBean(AdminProperties.class);
        Set<String> includes = adminProperties.getRoleCode();
        return CollectionUtils.contains(includes.iterator(), roleCode) ? Optional.of(true) : Optional.empty();
    }

    /**
     * 判断角色用户是不是管理员
     *
     * @return {@link Boolean} true 是 false不是
     */
    public static Optional<Boolean> isAdminRole(List<String> roleCode) {
        AdminProperties adminProperties = SpringUtil.getBean(AdminProperties.class);
        Set<String> includes = adminProperties.getRoleCode();
        return CollectionUtils.containsAny(includes, roleCode) ? Optional.of(true) : Optional.empty();
    }
}
