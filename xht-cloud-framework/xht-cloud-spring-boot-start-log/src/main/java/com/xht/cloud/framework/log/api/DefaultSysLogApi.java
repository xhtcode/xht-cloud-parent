package com.xht.cloud.framework.log.api;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.constant.RpcConstants;
import com.xht.cloud.framework.log.dto.SysLogDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@FeignClient(name = "xht-cloud-system") // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 操作日志")
public interface DefaultSysLogApi {

    String PREFIX = RpcConstants.RPC_API_PREFIX + "/sys/log";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建操作日志")
    R<Boolean> createOperateLog(@Valid @RequestBody SysLogDTO sysLogDTO);

}
