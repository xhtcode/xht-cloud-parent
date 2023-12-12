package com.xht.cloud.generate.module.template.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.generate.module.template.controller.request.GenCodeGroupAddRequest;
import com.xht.cloud.generate.module.template.controller.request.GenCodeGroupQueryRequest;
import com.xht.cloud.generate.module.template.controller.request.GenCodeGroupUpdateRequest;
import com.xht.cloud.generate.module.template.controller.response.GenCodeGroupResponse;
import com.xht.cloud.generate.module.template.service.IGenCodeGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/gen/code/group")
@RequiredArgsConstructor
@Tag(name = "")
public class GenCodeGroupController {

    private final IGenCodeGroupService genCodeGroupService;

    /**
     * 创建
     *
     * @param addRequest {@link GenCodeGroupAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-")
    @PostMapping
    public R<Boolean> create(@Validated(Create.class) @RequestBody GenCodeGroupAddRequest addRequest) {
        genCodeGroupService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenCodeGroupUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-")
    @PutMapping
    public R<Boolean> update(@Validated(Update.class) @RequestBody GenCodeGroupUpdateRequest updateRequest) {
        genCodeGroupService.update(updateRequest);
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
    public R<Boolean> remove(@RequestBody List<String> ids) {
        genCodeGroupService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link GenCodeGroupResponse}
     */
    @Operation(summary = "根据id查询详细-")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<GenCodeGroupResponse> findById(@PathVariable("id") String id) {
        return R.ok(genCodeGroupService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenCodeGroupQueryRequest}
     * @return {@link PageResponse<GenCodeGroupResponse>} 分页详情
     */
    @Operation(summary = "分页查询-")
    @GetMapping
    public R<List<GenCodeGroupResponse>> findPage(GenCodeGroupQueryRequest queryRequest) {
        return R.ok(genCodeGroupService.findPage(queryRequest));
    }

}
