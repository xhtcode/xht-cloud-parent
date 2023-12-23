package com.xht.cloud.sequence.controller;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.enums.IEnum;
import com.xht.cloud.framework.exception.system.SysException;
import com.xht.cloud.sequence.constant.GenerateIdType;
import com.xht.cloud.sequence.generate.GenerateIdFactory;
import com.xht.cloud.sequence.generate.GenerateIdHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@RestController
@RequestMapping("/generate")
@RequiredArgsConstructor
public class GenerateIdController {

    @GetMapping("/id")
    public R<String> generate(@RequestParam(value = "code", required = false, defaultValue = "1000") Integer code) {
        GenerateIdType generateIdType = IEnum.getIEnum(code, GenerateIdType.class);
        if (Objects.isNull(generateIdType)) {
            generateIdType = GenerateIdType.UUID;
        }
        GenerateIdHandler handler = GenerateIdFactory.getHandler(generateIdType).orElseThrow(() -> new SysException("找不到该类型"));
        return R.ok(handler.generate());
    }

}
