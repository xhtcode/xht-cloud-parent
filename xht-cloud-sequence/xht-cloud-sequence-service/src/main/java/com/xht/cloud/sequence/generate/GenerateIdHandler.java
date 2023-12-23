package com.xht.cloud.sequence.generate;

import com.xht.cloud.sequence.constant.GenerateIdType;
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
     * @return id
     */
    public abstract String generate();

    /**
     * 生成id
     *
     * @return {@link GenerateIdType}
     */
    protected abstract GenerateIdType getType();

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        GenerateIdFactory.register(getType(), this);
    }
}
