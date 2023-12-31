package com.xht.cloud.generate.module.config.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.generate.module.config.controller.request.GenCodeConfigAddRequest;
import com.xht.cloud.generate.module.config.controller.request.GenCodeConfigQueryRequest;
import com.xht.cloud.generate.module.config.controller.request.GenCodeConfigUpdateRequest;
import com.xht.cloud.generate.module.config.controller.response.GenCodeConfigResponse;
import com.xht.cloud.generate.module.config.service.IGenCodeConfigService;
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
 * 描述 ：代码生成器-配置中心
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/gen/code/config")
@RequiredArgsConstructor
@Tag(name = "代码生成器-配置中心")
public class GenCodeConfigController {

    private final IGenCodeConfigService genCodeConfigService;

    /**
     * 创建
     *
     * @param addRequest {@link GenCodeConfigAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-代码生成器-配置中心")
    @PostMapping
    public R<Boolean> create(@Validated(Create.class) @RequestBody GenCodeConfigAddRequest addRequest) {
        genCodeConfigService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link GenCodeConfigUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-代码生成器-配置中心")
    @PutMapping
    public R<Boolean> update(@Validated(Update.class) @RequestBody GenCodeConfigUpdateRequest updateRequest) {
        genCodeConfigService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-代码生成器-配置中心")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    public R<Boolean> remove(@RequestBody List<String> ids) {
        genCodeConfigService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link GenCodeConfigResponse}
     */
    @GetMapping("/{id}")
    public R<GenCodeConfigResponse> findById(@PathVariable("id") String id) {
        return R.ok(genCodeConfigService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link GenCodeConfigQueryRequest}
     * @return {@link PageResponse<GenCodeConfigResponse>} 分页详情
     */
    @GetMapping
    public R<PageResponse<GenCodeConfigResponse>> findPage(GenCodeConfigQueryRequest queryRequest) {
        return R.ok(genCodeConfigService.findPage(queryRequest));
    }


    /**
     * 查询全部
     *
     * @return {@link GenCodeConfigResponse}
     */
    @GetMapping("/list/")
    public R<List<GenCodeConfigResponse>> list() {
        return R.ok(genCodeConfigService.list());
    }
}
