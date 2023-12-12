package com.xht.cloud.authorization.server.service.oauth2;

import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.redis.key.RedisKeyTool;
import com.xht.cloud.framework.security.support.OAuth2AuthorizationTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 描述 ： OAuth2 授权信息
 *
 * @author : 小糊涂
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String AUTHORIZATION = "token";

    /**
     * 保存授权信息 {@link OAuth2Authorization}.
     *
     * @param authorization the {@link OAuth2Authorization}
     */
    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "授权信息不能为空!");
        log.debug("保存授权信息{}", authorization);
        OAuth2AuthorizationTool.getInstance()
                .isState(authorization, token -> {
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.STATE, String.valueOf(token));
                    redisTemplate.setValueSerializer(RedisSerializer.java());
                    redisTemplate.opsForValue()
                            .set(key, authorization, 10L, TimeUnit.MINUTES);
                })
                .isCode(authorization, token -> {
                    OAuth2AuthorizationCode authorizationCode = token.getToken();
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.CODE, authorizationCode.getTokenValue());
                    long between = ChronoUnit.MINUTES.between(Objects.requireNonNull(authorizationCode.getIssuedAt()), authorizationCode.getExpiresAt());
                    redisTemplate.setValueSerializer(RedisSerializer.java());
                    redisTemplate.opsForValue().set(key, authorization, between, TimeUnit.MINUTES);
                })
                .isAccessToken(authorization, token -> {
                    OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.ACCESS_TOKEN, accessToken.getTokenValue());
                    long between = ChronoUnit.SECONDS.between(Objects.requireNonNull(accessToken.getIssuedAt()), accessToken.getExpiresAt());
                    redisTemplate.setValueSerializer(RedisSerializer.java());
                    redisTemplate.opsForValue().set(key, authorization, between, TimeUnit.MINUTES);
                })
                .isRefreshToken(authorization, token -> {
                    OAuth2RefreshToken refreshToken = Objects.requireNonNull(authorization.getRefreshToken()).getToken();
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue());
                    long between = ChronoUnit.SECONDS.between(Objects.requireNonNull(refreshToken.getIssuedAt()), refreshToken.getExpiresAt());
                    redisTemplate.setValueSerializer(RedisSerializer.java());
                    redisTemplate.opsForValue().set(key, authorization, between, TimeUnit.MINUTES);
                }).other(authorization, token -> {
                    log.debug("未知的token保存，请注意 debug!");
                    throw new RuntimeException("未知的token保存，请注意 debug!");
                });
        ;
        log.debug("保存授权信息成功{}", authorization.getPrincipalName());
    }

    /**
     * 删除授权信息 {@link OAuth2Authorization}.
     *
     * @param authorization the {@link OAuth2Authorization}
     */
    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "授权信息不能为空!");
        final List<String> keys = new ArrayList<>();
        OAuth2AuthorizationTool.getInstance()
                .isState(authorization, token -> {
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.STATE, String.valueOf(token));
                    keys.add(key);
                })
                .isCode(authorization, token -> {
                    OAuth2AuthorizationCode authorizationCode = token.getToken();
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.CODE, authorizationCode.getTokenValue());
                    keys.add(key);
                })
                .isAccessToken(authorization, token -> {
                    OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.ACCESS_TOKEN, accessToken.getTokenValue());
                    keys.add(key);
                })
                .isRefreshToken(authorization, token -> {
                    OAuth2RefreshToken refreshToken = Objects.requireNonNull(authorization.getRefreshToken()).getToken();
                    String key = RedisKeyTool.createName(AUTHORIZATION, OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue());
                    keys.add(key);
                });
        redisTemplate.delete(keys);
        log.debug("删除授权信息成功{}", authorization.getPrincipalName());
    }

    /**
     * 根据 id 查询授权信息
     *
     * @param id the authorization identifier
     * @return the {@link OAuth2Authorization} if found, otherwise {@code null}
     */
    @Override
    public OAuth2Authorization findById(String id) {
        Assert.notNull(id, "授权信息`id`不能为空!");
        log.debug("根据id={}查询授权信息", id);
        return null;
    }

    /**
     * 根据token和token类型查询授权信息
     *
     * @param token     token令牌
     * @param tokenType token令牌类型 {@link OAuth2TokenType token type}
     * @return the {@link OAuth2Authorization} if found, otherwise {@code null}
     */
    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.notNull(token, "授权信息`token`不能为空!");
        log.debug("根据token={}和tokenType={}查询授权信息", token, tokenType);
        redisTemplate.setValueSerializer(RedisSerializer.java());
        if (Objects.isNull(tokenType)) {
            List<String> tokenTypes = Arrays.asList(OAuth2ParameterNames.STATE, OAuth2ParameterNames.CODE, OAuth2ParameterNames.ACCESS_TOKEN, OAuth2ParameterNames.REFRESH_TOKEN);
            for (String itemType : tokenTypes) {
                Object o = redisTemplate.opsForValue().get(RedisKeyTool.createName(AUTHORIZATION, itemType, token));
                if (Objects.nonNull(o)) {
                    return (OAuth2Authorization) o;
                }
            }
            return null;
        }
        return (OAuth2Authorization) redisTemplate.opsForValue().get(RedisKeyTool.createName(AUTHORIZATION, tokenType.getValue(), token));
    }


}
