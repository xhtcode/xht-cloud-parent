package com.xht.cloud.generate.module.type.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeAddRequest;
import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeQueryRequest;
import com.xht.cloud.generate.module.type.controller.request.GenColumnTypeUpdateRequest;
import com.xht.cloud.generate.module.type.controller.response.GenColumnTypeResponse;
import com.xht.cloud.generate.module.type.service.IGenColumnTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * 描述 ：代码生成器-字段类型对应
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/gen/column/type")
@RequiredArgsConstructor
@Tag(name = "代码生成器-字段类型对应")
public class GenColumnTypeController {

    private final IGenColumnTypeService genColumnTypeService;

    /**
     * 创建
     *
     * @param addRequest {@link GenColumnTypeAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-代码生成器-字段类型对应")
    @PostMapping
    public R<Boolean> create(@Validated(Create.class) @RequestBody GenColumnTypeAddRequest addRequest) {
        genColumnTypeService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenColumnTypeUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-代码生成器-字段类型对应")
    @PutMapping
    public R<Boolean> update(@Validated(Update.class) @RequestBody GenColumnTypeUpdateRequest updateRequest) {
        genColumnTypeService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-代码生成器-字段类型对应")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    public R<Boolean> remove(@RequestBody List<String> ids) {
        genColumnTypeService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link GenColumnTypeResponse}
     */
    @Operation(summary = "根据id查询详细-代码生成器-字段类型对应")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<GenColumnTypeResponse> findById(@PathVariable("id") String id) {
        return R.ok(genColumnTypeService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenColumnTypeQueryRequest}
     * @return {@link PageResponse<GenColumnTypeResponse>} 分页详情
     */
    @Operation(summary = "分页查询-代码生成器-字段类型对应")
    @GetMapping
    public R<PageResponse<GenColumnTypeResponse>> findPage(GenColumnTypeQueryRequest queryRequest) {
        return R.ok(genColumnTypeService.findPage(queryRequest));
    }

}
