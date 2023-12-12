package com.xht.cloud.system.module.area.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.safety.repeat.RepeatSubmit;
import com.xht.cloud.framework.security.annotaion.SkipAuthentication;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoAddRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoQueryRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoUpdateRequest;
import com.xht.cloud.system.module.area.controller.response.SysAreaInfoResponse;
import com.xht.cloud.system.module.area.convert.SysAreaInfoConvert;
import com.xht.cloud.system.module.area.dao.mapper.SysAreaInfoMapper;
import com.xht.cloud.system.module.area.service.ISysAreaInfoService;
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
 * 描述 ：地区信息
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/area/info")
@RequiredArgsConstructor
@Tag(name = "地区信息")
public class SysAreaInfoController {

    private final ISysAreaInfoService sysAreaInfoService;

    /**
     * 创建
     *
     * @param addRequest {@link SysAreaInfoAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-地区信息")
    @PostMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:area-info:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody SysAreaInfoAddRequest addRequest) {
        sysAreaInfoService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link SysAreaInfoUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-地区信息")
    @PutMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:area-info:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody SysAreaInfoUpdateRequest updateRequest) {
        sysAreaInfoService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-地区信息")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmit
    @PreAuthorize("@oauth2.hasAnyAuthority('sys:area-info:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        sysAreaInfoService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysAreaInfoResponse}
     */
    @Operation(summary = "根据id查询详细-地区信息")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysAreaInfoResponse> findById(@PathVariable("id") String id) {
        return ok(sysAreaInfoService.findById(id));
    }

    /**
     * 按条件查询全部
     *
     * @param queryRequest {@link SysAreaInfoQueryRequest}
     * @return {@link PageResponse<SysAreaInfoResponse>} 详情
     */
    @Operation(summary = "查询全部-地区信息")
    @GetMapping
    public R<List<SysAreaInfoResponse>> treeList(SysAreaInfoQueryRequest queryRequest) {
        List<SysAreaInfoResponse> list = sysAreaInfoService.list(queryRequest);
        return ok(list);
    }

    private final SysAreaInfoMapper sysAreaInfoMapper;
    private final SysAreaInfoConvert convert;

    @Operation(summary = "查询全部-地区信息")
    @GetMapping("/tree")
    @SkipAuthentication(value = false)
    public R<List<INode<String>>> info() {
        return ok(sysAreaInfoService.convert(new SysAreaInfoQueryRequest()));
    }

}
