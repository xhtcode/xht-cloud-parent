package com.xht.cloud.sequence.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.exception.system.SysException;
import com.xht.cloud.sequence.controller.request.IdRequest;
import com.xht.cloud.sequence.generate.GenerateIdFactory;
import com.xht.cloud.sequence.generate.GenerateIdHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 ：id生成
 *
 * @author : 小糊涂
 **/
@Slf4j
@RestController
@RequestMapping("/generate")
@RequiredArgsConstructor
public class GenerateIdController {

    @GetMapping("/id")
    public R<String> generate(@Validated IdRequest request) {
        GenerateIdHandler handler = GenerateIdFactory.getHandler(request.generateIdType()).orElseThrow(() -> new SysException("找不到该类型"));
        return R.ok(handler.generate(request));
    }

}
