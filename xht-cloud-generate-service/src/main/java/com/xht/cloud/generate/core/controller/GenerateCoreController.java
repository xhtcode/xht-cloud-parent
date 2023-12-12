package com.xht.cloud.generate.core.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.treenode.INode;
import com.xht.cloud.framework.exception.factory.ExceptionFactory;
import com.xht.cloud.generate.core.controller.request.GenCodeRequest;
import com.xht.cloud.generate.core.groups.DownGroups;
import com.xht.cloud.generate.core.service.IGenerateCoreService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述 ：代码生成器核心任务
 *
 * @author : 小糊涂
 **/
@Slf4j
@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class GenerateCoreController {

    private final IGenerateCoreService generateCoreService;


    /**
     * 查看代码
     *
     * @param tableId        表id
     * @param genCodeRequest {@link GenCodeRequest}
     * @return 代码文件树的格式
     */
    @GetMapping("/code/view/{tableId}")
    public R<List<INode<String>>> viewCode(@PathVariable("tableId") String tableId, @Validated GenCodeRequest genCodeRequest) {
        return R.ok(generateCoreService.viewCode(genCodeRequest, tableId));
    }

    /**
     * 代码下载
     *
     * @param genCodeRequest {@link GenCodeRequest}
     */
    @PostMapping("/code/download")
    public void downloadCode(HttpServletResponse response, @Validated(DownGroups.class) @RequestBody GenCodeRequest genCodeRequest) {
        try {
            byte[] data = generateCoreService.downloadCode(genCodeRequest);
            response.reset();
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.zip\"", IdUtil.simpleUUID()));
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.write(response.getOutputStream(), true, data);
        } catch (Exception e) {
            log.info("代码下载异常{}", e.getMessage(), e);
            throw ExceptionFactory.bizException("代码下载异常！");
        }
    }
}
