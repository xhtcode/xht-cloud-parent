package com.xht.cloud.system.module.log.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.system.module.log.controller.request.SysLogQueryRequest;
import com.xht.cloud.system.module.log.controller.response.SysLogResponse;
import com.xht.cloud.system.module.log.service.ISysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 ：系统操作日志记录
 *
 * @author : xht
 **/
@Slf4j
@RestController
@RequestMapping("/sys/log")
@RequiredArgsConstructor
@Tag(name = "系统操作日志记录")
public class SysLogController {

    private final ISysLogService sysLogService;

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysLogResponse}
     */
    @Operation(summary = "根据id查询详细-系统操作日志记录")
    @Parameter(name = "id", description = "id", required = true)
    @GetMapping("/{id}")
    public R<SysLogResponse> findById(@PathVariable("id") String id) {
        return R.ok(sysLogService.findById(id));
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysLogQueryRequest}
     * @return {@link PageResponse<SysLogResponse>} 分页详情
     */
    @Operation(summary = "分页查询-系统操作日志记录")
    @GetMapping
    public R<PageResponse<SysLogResponse>> findPage(SysLogQueryRequest queryRequest) {
        return R.ok(sysLogService.findPage(queryRequest));
    }

}
