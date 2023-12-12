package com.xht.cloud.authorization.server.service;

import com.xht.cloud.authorization.server.dataobject.ClientDO;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public interface ClientService {

    ClientDO findByClientId(String clientId);
}
