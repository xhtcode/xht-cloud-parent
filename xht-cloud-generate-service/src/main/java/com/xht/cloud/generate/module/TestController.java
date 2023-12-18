package com.xht.cloud.generate.module;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.safety.limit.HttpLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public R<String> success() {
        return R.ok("success");
    }
}
