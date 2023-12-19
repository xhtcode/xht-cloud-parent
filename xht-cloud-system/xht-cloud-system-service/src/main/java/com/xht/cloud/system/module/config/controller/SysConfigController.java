package com.xht.cloud.system.module.config.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.safety.repeat.annotation.RepeatSubmitLimit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.config.controller.request.SysConfigAddRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigQueryRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigUpdateRequest;
import com.xht.cloud.system.module.config.controller.response.SysConfigResponse;
import com.xht.cloud.system.module.config.service.ISysConfigService;
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
 * 描述 ：系统配置信息
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/config")
@RequiredArgsConstructor
@Tag(name = "系统配置信息")
public class SysConfigController {

    private final ISysConfigService sysConfigService;

    /**
     * 创建
     *
     * @param addRequest {@link SysConfigAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-系统配置信息")
    @PostMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:config:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysConfigAddRequest addRequest) {
        sysConfigService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysConfigUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-系统配置信息")
    @PutMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:config:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysConfigUpdateRequest updateRequest) {
        sysConfigService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-系统配置信息")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:config:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysConfigService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysConfigResponse}
     */
    @Operation(summary = "根据id查询详细-系统配置信息")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysConfigResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysConfigService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysConfigQueryRequest}
     * @return {@link PageResponse<SysConfigResponse>} 分页详情
     */
    @Operation(summary = "分页查询-系统配置信息")
    @GetMapping
    public R<PageResponse<SysConfigResponse>> findPage(SysConfigQueryRequest queryRequest) {
        return R.ok(sysConfigService.findPage(queryRequest));
    }

}
