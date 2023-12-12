package com.xht.cloud.system.module.user.api;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.security.annotaion.SkipAuthentication;
import com.xht.cloud.system.api.user.UserFeignApi;
import com.xht.cloud.system.api.user.dto.response.UserResponseDTO;
import com.xht.cloud.system.module.user.controller.response.SysUserVo;
import com.xht.cloud.system.module.user.convert.SysUserConvert;
import com.xht.cloud.system.module.user.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Validated
@RestController
@RequiredArgsConstructor
public class UserApiImpl implements UserFeignApi {

    private final ISysUserService userService;

    private final SysUserConvert sysUserConvert;

    /**
     * 根据用户名查询用户账号
     *
     * @param userName 用户账号
     * @return {@link UserResponseDTO}
     */
    @Override
    @SkipAuthentication
    public R<UserResponseDTO> findUserByUserName(String userName) {
        SysUserVo byUserName = userService.findByUserName(userName);
        return R.ok(sysUserConvert.toDTO(byUserName));
    }
}
