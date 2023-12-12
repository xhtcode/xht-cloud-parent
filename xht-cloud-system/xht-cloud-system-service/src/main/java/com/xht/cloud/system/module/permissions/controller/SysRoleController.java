package com.xht.cloud.system.module.permissions.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.safety.repeat.RepeatSubmit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleAddRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleQueryRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleUpdateRequest;
import com.xht.cloud.system.module.permissions.controller.response.SysRoleResponse;
import com.xht.cloud.system.module.permissions.service.ISysRoleService;
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
 * 描述 ：系统角色表
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/role")
@RequiredArgsConstructor
@Tag(name = "系统角色表")
public class SysRoleController {

    private final ISysRoleService sysRoleService;

    /**
     * 创建
     *
     * @param addRequest {@link SysRoleAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-系统角色表")
    @PostMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:role:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysRoleAddRequest addRequest) {
        sysRoleService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysRoleUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-系统角色表")
    @PutMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:role:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysRoleUpdateRequest updateRequest) {
        sysRoleService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-系统角色表")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:role:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysRoleService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysRoleResponse}
     */
    @Operation(summary = "根据id查询详细-系统角色表")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysRoleResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysRoleService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysRoleQueryRequest}
     * @return {@link PageResponse<SysRoleResponse>} 分页详情
     */
    @Operation(summary = "分页查询-系统角色表")
    @GetMapping
    public R<PageResponse<SysRoleResponse>> findPage(SysRoleQueryRequest queryRequest) {
        return R.ok(sysRoleService.findPage(queryRequest));
    }

    /**
     * 查询全部
     *
     * @param queryRequest {@link SysRoleQueryRequest}
     * @return {@link List<SysRoleResponse>} 详情
     */
    @Operation(summary = "分页查询-系统角色表")
    @GetMapping("/list/")
    public R<List<SysRoleResponse>> findList(SysRoleQueryRequest queryRequest) {
        return R.ok(sysRoleService.list(queryRequest));
    }
}
