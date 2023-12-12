package com.xht.cloud.authorization.server.repository;

import com.xht.cloud.authorization.server.dataobject.AuthorizationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <h1>描述 ：</h1>
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationDO, String> {
    Optional<AuthorizationDO> findByState(String state);
    Optional<AuthorizationDO> findByAuthorizationCodeValue(String authorizationCode);
    Optional<AuthorizationDO> findByAccessTokenValue(String accessToken);
    Optional<AuthorizationDO> findByRefreshTokenValue(String refreshToken);
    Optional<AuthorizationDO> findByOidcIdTokenValue(String idToken);
    Optional<AuthorizationDO> findByUserCodeValue(String userCode);
    Optional<AuthorizationDO> findByDeviceCodeValue(String deviceCode);
    @Query("select a from AuthorizationDO a where a.state = :token" +
            " or a.authorizationCodeValue = :token" +
            " or a.accessTokenValue = :token" +
            " or a.refreshTokenValue = :token"
    )
    Optional<AuthorizationDO> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue(@Param("token") String token);

    /**
     * 根据客户端ID和用户名查询未过期Token
     *
     * @param registeredClientId 客户端ID
     * @param principalName      用户名称
     * @param localDateTime      时间
     * @return 认证信息列表
     */
    List<AuthorizationDO> findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(String registeredClientId, String principalName, LocalDateTime localDateTime);

    /**
     * 根据 RefreshToken 过期时间，清理历史 Token信息
     * <p>
     * OAuth2Authorization 表中存在 AccessToken、OidcToken、RefreshToken 等三个过期时间。
     * 正常的删除逻辑应该是三个过期时间都已经过期才行。但是有特殊情况：
     * 1. OidcToken 的过期时间有可能为空，这就增加了 SQL 处理的复杂度。
     * 2. 逻辑上 RefreshToken 的过期应该是最长的(这是默认配置正确的情况)
     * 因此，目前就简单的根据 RefreshToken过期时间进行处理
     *
     * @param localDateTime 时间
     */
    @Modifying
    @Transactional
    void deleteByRefreshTokenExpiresAtBefore(LocalDateTime localDateTime);
}
