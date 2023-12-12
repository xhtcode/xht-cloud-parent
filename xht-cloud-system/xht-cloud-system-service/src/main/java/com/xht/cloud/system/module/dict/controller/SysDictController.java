package com.xht.cloud.system.module.dict.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.safety.repeat.RepeatSubmit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.dict.controller.request.SysDictAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictResponse;
import com.xht.cloud.system.module.dict.controller.response.SysDictVo;
import com.xht.cloud.system.module.dict.service.ISysDictService;
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
 * 描述 ：字典
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/dict")
@RequiredArgsConstructor
@Tag(name = "字典")
public class SysDictController {

    private final ISysDictService sysDictService;

    /**
     * 创建
     *
     * @param addRequest {@link SysDictAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-字典")
    @PostMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysDictAddRequest addRequest) {
        sysDictService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDictUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-字典")
    @PutMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysDictUpdateRequest updateRequest) {
        sysDictService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-字典")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysDictService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDictResponse}
     */
    @Operation(summary = "根据id查询详细-字典")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysDictResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysDictService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictQueryRequest}
     * @return {@link PageResponse<SysDictResponse>} 分页详情
     */
    @Operation(summary = "分页查询-字典")
    @GetMapping
    public R<PageResponse<SysDictResponse>> findPage(SysDictQueryRequest queryRequest) {
        return R.ok(sysDictService.findPage(queryRequest));
    }

    /**
     * 根据dictCode 字典编码查询详细
     *
     * @param dictCode {@link String} 字典编码
     * @return {@link SysDictVo}
     */
    @Operation(summary = "根据id查询详细-字典")
    @Parameter(name = "dictCode", description = "字典code", required = true)
    @GetMapping("/code/{dictCode}")
    public R<SysDictVo> findByCode(@PathVariable("dictCode") String dictCode) {
        return ok(sysDictService.findByCode(dictCode));
    }

}
