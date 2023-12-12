package com.xht.cloud.system.module.permissions.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.safety.repeat.RepeatSubmit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuAddRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuQueryRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuUpdateRequest;
import com.xht.cloud.system.module.permissions.controller.response.SysMenuResponse;
import com.xht.cloud.system.module.permissions.service.ISysMenuService;
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
 * 描述 ：菜单权限
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/menu")
@RequiredArgsConstructor
@Tag(name = "菜单权限")
public class SysMenuController {

    private final ISysMenuService sysMenuService;

    /**
     * 创建
     *
     * @param addRequest {@link SysMenuAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-菜单权限")
    @PostMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:menu:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysMenuAddRequest addRequest) {
        sysMenuService.validationAndFormat(null, addRequest);
        sysMenuService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysMenuUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-菜单权限")
    @PutMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:menu:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysMenuUpdateRequest updateRequest) {
        sysMenuService.validationAndFormat(updateRequest.getId(), updateRequest);
        sysMenuService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-菜单权限")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:menu:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysMenuService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysMenuResponse}
     */
    @Operation(summary = "根据id查询详细-菜单权限")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysMenuResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysMenuService.findById(id));
    }

    /**
     * 条件查询全部(树)
     *
     * @param queryRequest {@link SysMenuQueryRequest}
     * @return {@link List<SysMenuResponse>} 菜单数据树
     */
    @Operation(summary = "查询全部(树)-菜单权限")
    @GetMapping
    public R<List<INode<String>>> treeList(SysMenuQueryRequest queryRequest) {
        List<SysMenuResponse> list = sysMenuService.list(queryRequest);
        return R.ok(sysMenuService.convert(list, Boolean.FALSE));
    }

}
