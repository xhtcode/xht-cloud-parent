package com.xht.cloud.framework.security.core.userdetails.impl;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.ROptional;
import com.xht.cloud.framework.security.Oauth2Properties;
import com.xht.cloud.framework.security.api.UserInfoApi;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import com.xht.cloud.framework.security.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 描述 ：配置 {@code UserDetailsService} 根据账号查询用户信息查询密码
 *
 * @author : 小糊涂
 **/
@RefreshScope
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserInfoApi userInfoApi;

    private final Oauth2Properties oauth2Properties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserResponseDTO> userByUserName = userInfoApi.findUserByUserName(username, oauth2Properties.getRpcHeaderValue());
        UserResponseDTO userResponseDTO = ROptional.ofNullable(userByUserName).getData().orElse(null);
        if (userResponseDTO == null) {
            throw new UsernameNotFoundException(username);
        }
        return convert(userResponseDTO);
    }
    /**
     * 转换类
     *
     * @param userInfo 用户信息
     * @return {@link  CustomUserDetails}
     */
    private CustomUserDetails convert(UserResponseDTO userInfo) {
        Set<String> authorities = userInfo.getAuthorities();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(authorities)) {
            grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return getCustomUserDetails(userInfo, grantedAuthorities);
    }

    private static CustomUserDetails getCustomUserDetails(UserResponseDTO userInfo, List<SimpleGrantedAuthority> grantedAuthorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails(userInfo.getUserName(), userInfo.getPassWord(), grantedAuthorities);
        customUserDetails.setPassWordSalt(userInfo.getPassWordSalt());
        customUserDetails.setUserId(userInfo.getId());
        customUserDetails.setEnabled(true);
        customUserDetails.setUserType(userInfo.getUserType());
        customUserDetails.setDeptId(userInfo.getDeptId());
        customUserDetails.setDataScope(userInfo.getDataScope());
        customUserDetails.setRoleCode(userInfo.getRoleCode());
        return customUserDetails;
    }
}
