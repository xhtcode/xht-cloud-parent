package com.xht.cloud.system.module.user.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.core.treenode.TreeNode;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.exception.constant.ErrorCodeConstants;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import com.xht.cloud.system.enums.MenuTypeEnums;
import com.xht.cloud.system.exceptions.PermissionException;
import com.xht.cloud.system.module.permissions.controller.response.SysMenuResponse;
import com.xht.cloud.system.module.permissions.service.ISysMenuService;
import com.xht.cloud.system.module.user.controller.request.SysUserProfileRequest;
import com.xht.cloud.system.module.user.controller.request.UpdatePassWordRequest;
import com.xht.cloud.system.module.user.controller.response.SysUserVo;
import com.xht.cloud.system.module.user.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@RestController
@RequestMapping("/sys/user/profile")
@RequiredArgsConstructor
@Tag(name = "用户信息查询")
public class SysUserProfileController {

    private final ISysUserService sysUserService;

    private final ISysMenuService sysMenuService;


    /**
     * 查询当前登录用户信息
     *
     * @return {@link SysUserVo}
     */
    @Operation(summary = "查询当前登录用户信息")
    @GetMapping("/info")
    public R<SysUserVo> findByUserName() {
        SysUserVo byUserName = sysUserService.findByUserName(SecurityContextUtil.getUserName());
        if (Objects.nonNull(byUserName) && Objects.nonNull(byUserName.getSysUser())) {
            byUserName.getSysUser().setPassWord(null);
            byUserName.getSysUser().setPassWordSalt(null);
        }
        return R.ok(byUserName);
    }

    /**
     * 当前登录用户所拥有的菜单路由
     *
     * @return {@link TreeNode}
     */
    @Operation(summary = "当前用户菜单路由")
    @GetMapping(value = "/getRouters")
    public R<List<INode<String>>> getRouters() {
        CustomUserDetails user = SecurityContextUtil.getUser().orElseThrow(() -> new PermissionException("用户未登录！"));
        List<SysMenuResponse> sysMenuResponses = sysMenuService.selectByUserId(user.getUserId(),
                new MenuTypeEnums[]{MenuTypeEnums.M, MenuTypeEnums.C});
        return R.ok(sysMenuService.convert(sysMenuResponses, Boolean.TRUE));
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public R<Boolean> updateUserProfile(@Valid @RequestBody SysUserProfileRequest request) {
        sysUserService.updateUserProfile(SecurityContextUtil.getUserName(), request);
        return R.ok(true);
    }

    @PutMapping("/update/password")
    @Operation(summary = "修改用户个人密码")
    public R<Boolean> updateUserProfilePassword(@Valid @RequestBody UpdatePassWordRequest request) {
        sysUserService.updateUserPassword(SecurityContextUtil.getUserName(), request);
        return R.ok(true);
    }

    @RequestMapping(value = "/update/avatar", method = {RequestMethod.PUT})
    @Operation(summary = "上传用户个人头像")
    public R<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            Assert.fail(ErrorCodeConstants.FILE_NOT_EXISTS);
        }
        String avatar = sysUserService.updateUserAvatar(SecurityContextUtil.getUserName(), file.getInputStream());
        return R.ok(avatar);
    }

}
