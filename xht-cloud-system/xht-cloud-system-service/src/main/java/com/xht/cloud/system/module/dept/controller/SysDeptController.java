package com.xht.cloud.system.module.dept.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.safety.repeat.RepeatSubmitLimit;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.dept.controller.request.SysDeptAddRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptQueryRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptUpdateRequest;
import com.xht.cloud.system.module.dept.controller.response.SysDeptResponse;
import com.xht.cloud.system.module.dept.service.ISysDeptService;
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
 * 描述 ：部门
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/dept")
@RequiredArgsConstructor
@Tag(name = "部门")
public class SysDeptController {

    private final ISysDeptService sysDeptService;

    /**
     * 创建
     *
     * @param addRequest {@link SysDeptAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-部门")
    @PostMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dept:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysDeptAddRequest addRequest) throws Exception {
        sysDeptService.validate(addRequest);
        sysDeptService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysDeptUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-部门")
    @PutMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dept:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysDeptUpdateRequest updateRequest) throws Exception {
        sysDeptService.validate(updateRequest);
        sysDeptService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-部门")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:dept:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysDeptService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysDeptResponse}
     */
    @Operation(summary = "根据id查询详细-部门")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysDeptResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysDeptService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysDeptQueryRequest}
     * @return {@link PageResponse<SysDeptResponse>} 分页详情
     */
    @Operation(summary = "分页查询-部门")
    @GetMapping
    public R<List<INode<String>>> findTree(SysDeptQueryRequest queryRequest) {
        List<SysDeptResponse> list = sysDeptService.findList(queryRequest);
        return R.ok(sysDeptService.convert(list));
    }

}
