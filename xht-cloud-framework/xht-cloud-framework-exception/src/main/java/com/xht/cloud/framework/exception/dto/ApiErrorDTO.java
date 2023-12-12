package com.xht.cloud.framework.exception.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 描述 ：api 异常的dto类
 * 记录当前api的基本的信息
 *
 * @author : 小糊涂
 **/
public record ApiErrorDTO(String path, String message, String status, LocalDateTime now) {

    public ApiErrorDTO {
        if (Objects.isNull(now)) {
            now = LocalDateTime.now();
        }
    }

    public ApiErrorDTO(String path, String message, String status) {
        this(path, message, status, LocalDateTime.now());
    }
}
