package com.xht.cloud.authorization.server.service.impl;

import com.xht.cloud.authorization.server.dataobject.ClientDO;
import com.xht.cloud.authorization.server.repository.ClientRepository;
import com.xht.cloud.authorization.server.service.ClientService;
import com.xht.cloud.framework.exception.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDO findByClientId(String clientId) {
        Assert.notNull(clientId);
        return clientRepository.findByClientId(clientId).orElse(null);
    }
}
