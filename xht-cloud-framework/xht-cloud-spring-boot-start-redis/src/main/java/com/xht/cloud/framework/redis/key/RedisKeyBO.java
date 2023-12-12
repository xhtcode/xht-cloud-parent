package com.xht.cloud.framework.redis.key;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
public class RedisKeyBO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * key名称
     */
    private final String keyName;

    /**
     * 过期时间
     */
    private final Long timeOut;

    /**
     * 过期时间
     */
    private final TimeUnit timeType;

    public RedisKeyBO(String keyName) {
        this.keyName = keyName;
        this.timeOut = null;
        this.timeType = null;
    }

    public RedisKeyBO(String keyName, Long timeOut) {
        this.keyName = keyName;
        this.timeOut = timeOut;
        this.timeType = TimeUnit.MINUTES;
    }

    public RedisKeyBO(String keyName, Long timeOut, TimeUnit timeType) {
        this.keyName = keyName;
        this.timeOut = timeOut;
        this.timeType = timeType;
    }

    /**
     * 判断是否过期类型key
     */
    public boolean isExpireKey() {
        return Objects.isNull(timeOut) || Objects.isNull(timeType);
    }

}
