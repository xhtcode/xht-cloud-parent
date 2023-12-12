package com.xht.cloud.authorization.server.repository;

import com.xht.cloud.authorization.server.dataobject.AuthorizationConsentDO;
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
public interface AuthorizationConsentRepository extends JpaRepository<AuthorizationConsentDO, AuthorizationConsentDO.AuthorizationConsentId> {
    Optional<AuthorizationConsentDO> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}
