package com.xht.cloud.system.module.oauth2.dao.mapper;

import com.xht.cloud.framework.mybatis.mapper.BaseMapperX;
import com.xht.cloud.system.module.oauth2.dao.dataobject.Oauth2RegisteredClientDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述 ：oauth2 客户端信息
 *
 * @author : xht
 **/
@Mapper
public interface Oauth2RegisteredClientMapper extends BaseMapperX<Oauth2RegisteredClientDO> {

}
