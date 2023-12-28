package com.xht.cloud.sequence.generate;

import com.xht.cloud.sequence.constant.GenerateIdType;
import com.xht.cloud.sequence.controller.request.IdRequest;
import org.springframework.beans.factory.InitializingBean;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public abstract class GenerateIdHandler implements InitializingBean {

    /**
     * 生成id
     *
     * @param  request {@link IdRequest}
     * @return id
     */
    public abstract String generate(IdRequest request);

    /**
     * 生成id
     *
     * @return {@link GenerateIdType}
     */
    protected abstract GenerateIdType getType();

    @Override
    @SuppressWarnings("all")
    public void afterPropertiesSet() throws Exception {
        GenerateIdFactory.register(getType(), this);
    }
}
