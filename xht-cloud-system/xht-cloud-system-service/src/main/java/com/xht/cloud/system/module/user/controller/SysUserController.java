package com.xht.cloud.system.module.user.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.safety.repeat.RepeatSubmit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.user.controller.request.SysUserAddRequest;
import com.xht.cloud.system.module.user.controller.request.SysUserBaseAddUpdate;
import com.xht.cloud.system.module.user.controller.request.SysUserQueryRequest;
import com.xht.cloud.system.module.user.controller.request.SysUserUpdateRequest;
import com.xht.cloud.system.module.user.controller.response.SysUserResponse;
import com.xht.cloud.system.module.user.controller.response.SysUserVo;
import com.xht.cloud.system.module.user.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.xht.cloud.framework.core.api.R.ok;

/**
 * 描述 ：用户
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
@Tag(name = "用户信息管理")
public class SysUserController {

    private final ISysUserService sysUserService;

    /**
     * 创建
     *
     * @param request {@link SysUserAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-用户")
    @PostMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:user:add')")
    public R<String> create(@Validated(Create.class) @RequestBody SysUserBaseAddUpdate request) {
        return ok(sysUserService.create(request));
    }

    /**
     * 根据id修改
     *
     * @param request {@link SysUserUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-用户")
    @PutMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:user:edit')")
    public R<String> update(@Validated(Update.class) @RequestBody SysUserBaseAddUpdate request) {
        return ok(sysUserService.update(request));
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-用户")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:user:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysUserService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysUserResponse}
     */
    @Operation(summary = "根据id查询详细-用户")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/detail/{id}")
    public R<SysUserVo> findById(@PathVariable("id") String id) {
        return R.ok(sysUserService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysUserQueryRequest}
     * @return {@link PageResponse<SysUserResponse>} 分页详情
     */
    @Operation(summary = "分页查询-用户")
    @GetMapping
    public R<PageResponse<SysUserResponse>> findPage(SysUserQueryRequest queryRequest) {
        return R.ok(sysUserService.findPage(queryRequest));
    }

    /**
     * 重置账号密码
     *
     * @param userId 用户id
     */
    @Operation(summary = "分页查询-用户")
    @PutMapping("/reset/password/{userId}")
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:user:password:reset')")
    public R<String> resetUserPassword(@PathVariable("userId") String userId) {
        return R.ok(sysUserService.resetUserPassword(userId));
    }
}
