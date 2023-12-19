package com.xht.cloud.system.module.oauth2.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.safety.repeat.annotation.RepeatSubmitLimit;
import com.xht.cloud.framework.security.annotaion.SkipAuthentication;
import com.xht.cloud.framework.web.validation.group.Create;
import com.xht.cloud.framework.web.validation.group.Update;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientAddRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientQueryRequest;
import com.xht.cloud.system.module.oauth2.controller.request.Oauth2RegisteredClientUpdateRequest;
import com.xht.cloud.system.module.oauth2.controller.response.Oauth2RegisteredClientResponse;
import com.xht.cloud.system.module.oauth2.service.IOauth2RegisteredClientService;
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
 * 描述 ：oauth2 客户端信息
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/oauth2/registered/client")
@RequiredArgsConstructor
@Tag(name = "oauth2 客户端信息")
public class Oauth2RegisteredClientController {

    private final IOauth2RegisteredClientService oauth2RegisteredClientService;

    /**
     * 创建
     *
     * @param addRequest {@link Oauth2RegisteredClientAddRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "创建-oauth2 客户端信息")
    @PostMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('oauth2:registered-client:add')")
    public R<Boolean> create(@Validated(Create.class) @RequestBody Oauth2RegisteredClientAddRequest addRequest) {
        oauth2RegisteredClientService.create(addRequest);
        return ok(true);
    }

    /**
     * 根据id修改
     *
     * @param updateRequest {@link Oauth2RegisteredClientUpdateRequest}
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id修改-oauth2 客户端信息")
    @PutMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('oauth2:registered-client:edit')")
    public R<Boolean> update(@Validated(Update.class) @RequestBody Oauth2RegisteredClientUpdateRequest updateRequest) {
        oauth2RegisteredClientService.update(updateRequest);
        return ok(true);
    }

    /**
     * 根据id删除
     *
     * @param ids {@link List <String>} id集合
     * @return {@link Boolean} true成功 false失败
     */
    @Operation(summary = "根据id删除-oauth2 客户端信息")
    @Parameter(name = "id", description = "id", required = true)
    @DeleteMapping
    @RepeatSubmitLimit
    @PreAuthorize("@oauth2.hasAnyAuthority('oauth2:registered-client:remove')")
    public R<Boolean> remove(@RequestBody List<String> ids) {
        oauth2RegisteredClientService.remove(ids);
        return ok(true);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link Oauth2RegisteredClientResponse}
     */
    @Operation(summary = "根据id查询详细-oauth2 客户端信息")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    @SkipAuthentication
    public R<Oauth2RegisteredClientResponse> findById(@PathVariable("id") String id) {
        return R.ok(oauth2RegisteredClientService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link Oauth2RegisteredClientQueryRequest}
     * @return {@link PageResponse<Oauth2RegisteredClientResponse>} 分页详情
     */
    @Operation(summary = "分页查询-oauth2 客户端信息")
    @GetMapping
    public R<PageResponse<Oauth2RegisteredClientResponse>> findPage(Oauth2RegisteredClientQueryRequest queryRequest) {
        return R.ok(oauth2RegisteredClientService.findPage(queryRequest));
    }

}
