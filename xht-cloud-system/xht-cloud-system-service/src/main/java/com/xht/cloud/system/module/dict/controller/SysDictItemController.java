package com.xht.cloud.system.module.dict.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.safety.repeat.RepeatSubmit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Query;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.exceptions.DictException;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictItemResponse;
import com.xht.cloud.system.module.dict.controller.response.SysDictResponse;
import com.xht.cloud.system.module.dict.service.ISysDictItemService;
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
import java.util.Objects;

import static com.xht.cloud.framework.core.api.R.ok;

/**
 * 描述 ：字典数据
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/dict/item")
@RequiredArgsConstructor
@Tag(name = "字典数据")
public class SysDictItemController {

    private final ISysDictItemService sysDictItemService;

    private final ISysDictService sysDictService;

    /**
     * 创建
     *
     * @param addRequest {@link SysDictItemAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-字典数据")
    @PostMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict-item:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysDictItemAddRequest addRequest) {
        SysDictResponse byId = sysDictService.findById(addRequest.getDictId());
        if (Objects.isNull(byId)) {
            throw new DictException("上级字典不存在！");
        }
        sysDictItemService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDictItemUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-字典数据")
    @PutMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict-item:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysDictItemUpdateRequest updateRequest) {
        SysDictResponse byId = sysDictService.findById(updateRequest.getDictId());
        if (Objects.isNull(byId)) {
            throw new DictException("上级字典不存在或未启用！");
        }
        sysDictItemService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-字典数据")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dict-item:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysDictItemService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDictItemResponse}
     */
    @Operation(summary = "根据id查询详细-字典数据")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysDictItemResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysDictItemService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDictItemQueryRequest}
     * @return {@link PageResponse<SysDictItemResponse>} 分页详情
     */
    @Operation(summary = "分页查询-字典数据")
    @GetMapping
    public R<PageResponse<SysDictItemResponse>> findPage(@Validated(Query.class) SysDictItemQueryRequest queryRequest) {
        SysDictResponse byId = sysDictService.findById(queryRequest.getDictId());
        if (Objects.isNull(byId)) {
            throw new DictException("上级字典不存在或未启用！");
        }
        return R.ok(sysDictItemService.findPage(queryRequest));
    }

}
