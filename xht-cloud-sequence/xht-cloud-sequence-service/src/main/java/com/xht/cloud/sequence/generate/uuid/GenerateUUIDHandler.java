package com.xht.cloud.sequence.generate.uuid;

import com.xht.cloud.sequence.constant.GenerateIdType;
import com.xht.cloud.sequence.generate.GenerateIdHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class GenerateUUIDHandler extends GenerateIdHandler {

    /**
     * 生成id
     *
     * @return id
     */
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成id
     *
     * @return {@link GenerateIdType}
     */
    @Override
    protected GenerateIdType getType() {
        return GenerateIdType.UUID;
    }

}
