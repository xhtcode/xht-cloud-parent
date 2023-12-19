package com.xht.cloud.system.module.poetry.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.safety.repeat.annotation.RepeatSubmitLimit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryAddRequest;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryQueryRequest;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryUpdateRequest;
import com.xht.cloud.system.module.poetry.controller.response.SysDictPoetryResponse;
import com.xht.cloud.system.module.poetry.service.ISysDictPoetryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.xht.cloud.framework.core.api.R.ok;

/**
 * 描述 ：
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/dict/poetry")
@RequiredArgsConstructor
@Tag(name = "")
public class SysDictPoetryController {

    private final ISysDictPoetryService sysDictPoetryService;

    /**
     * 创建
     *
     * @param addRequest {@link SysDictPoetryAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-")
    @PostMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict-poetry:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysDictPoetryAddRequest addRequest) {
        sysDictPoetryService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDictPoetryUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-")
    @PutMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict-poetry:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysDictPoetryUpdateRequest updateRequest) {
        sysDictPoetryService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict-poetry:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysDictPoetryService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDictPoetryResponse}
     */
    @Operation(summary = "根据id查询详细-")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysDictPoetryResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysDictPoetryService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictPoetryQueryRequest}
     * @return {@link PageResponse<SysDictPoetryResponse>} 分页详情
     */
    @Operation(summary = "分页查询-")
    @GetMapping
    public R<PageResponse<SysDictPoetryResponse>> findPage(SysDictPoetryQueryRequest queryRequest) {
        return R.ok(sysDictPoetryService.findPage(queryRequest));
    }

}
