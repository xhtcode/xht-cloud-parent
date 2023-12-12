package com.xht.cloud.authorization.server.repository;


import com.xht.cloud.authorization.server.dataobject.ClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1>描述 ：</h1>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Repository
public interface ClientRepository extends JpaRepository<ClientDO, String> {
    Optional<ClientDO> findByClientId(String clientId);
}

