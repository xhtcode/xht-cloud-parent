package com.xht.cloud.code.generate.exception;

import lombok.Getter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class GenerateException extends RuntimeException {

    @Getter
    private final String message;

    public GenerateException(String message) {
        super(message);
        this.message = message;
    }
}
