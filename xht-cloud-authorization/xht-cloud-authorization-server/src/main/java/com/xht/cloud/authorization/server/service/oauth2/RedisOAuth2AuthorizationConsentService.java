package com.xht.cloud.authorization.server.service.oauth2;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.redis.key.RedisKeyTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * 描述 ：OAuth2 授权许可
 *
 * @author : 小糊涂
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final JpaRegisteredClientService clientService;
    private static final String AUTHORIZATION = "token";

    /**
     * 保存  OAuth2 授权许可{@link OAuth2AuthorizationConsent}.
     *
     * @param authorizationConsent the {@link OAuth2AuthorizationConsent}
     */
    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        log.info("保存OAuth2 授权许可");
        Assert.notNull(authorizationConsent);
        redisTemplate.setValueSerializer(RedisSerializer.java());
        RegisteredClient byClientId = clientService.findByClientId(authorizationConsent.getRegisteredClientId());
        assert byClientId != null;
        Duration accessTokenTimeToLive = byClientId.getTokenSettings().getAccessTokenTimeToLive();
        redisTemplate.opsForValue()
                .set(RedisKeyTool.createName(AUTHORIZATION, authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName()), authorizationConsent, accessTokenTimeToLive.get(ChronoUnit.SECONDS), TimeUnit.SECONDS);
    }

    /**
     * 删除OAuth2 授权许可 {@link OAuth2AuthorizationConsent}.
     *
     * @param authorizationConsent the {@link OAuth2AuthorizationConsent}
     */
    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        String key = RedisKeyTool.createName(AUTHORIZATION, authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
        log.info("删除OAuth2 授权许可{}", key);
        redisTemplate.delete(key);
    }

    /**
     * 查询 OAuth2 授权许可
     *
     * @param registeredClientId 注册的客户端id {@link RegisteredClient}
     * @param principalName      the name of the {@link Principal}
     * @return the {@link OAuth2AuthorizationConsent} if found, otherwise {@code null}
     */
    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        Assert.isFalse(!StringUtils.hasText(registeredClientId), "registeredClientId cannot be empty");
        Assert.notNull(!StringUtils.hasText(principalName), "principalName cannot be empty");
        log.info("根据 {} 和 {} 查询 OAuth2 授权许可", registeredClientId, principalName);
        redisTemplate.setValueSerializer(RedisSerializer.java());
        return (OAuth2AuthorizationConsent) redisTemplate.opsForValue().get(RedisKeyTool.createName(AUTHORIZATION, registeredClientId, principalName));
    }

}
