package com.xht.cloud.system.module.oauth2.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.oauth2.dao.dataobject.Oauth2RegisteredClientDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：oauth2 客户端信息
 *
 * @author : xht
 **/
public final class Oauth2RegisteredClientWrapper implements EntityWrapper<Oauth2RegisteredClientDO> {

    /**
     * 私有化构造器
     */
    private Oauth2RegisteredClientWrapper() {
    }

    /**
     * 获取实例
     */
    public static Oauth2RegisteredClientWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final Oauth2RegisteredClientWrapper wrapper;

        Instance() {
            wrapper = new Oauth2RegisteredClientWrapper();
        }

        public Oauth2RegisteredClientWrapper getInstance() {
            return wrapper;
        }
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<Oauth2RegisteredClientDO> lambdaQuery(Oauth2RegisteredClientDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<Oauth2RegisteredClientDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), Oauth2RegisteredClientDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getClientId()), Oauth2RegisteredClientDO::getClientId, entity.getClientId())
                .eq(!ObjectUtils.isEmpty(entity.getClientIdIssuedAt()), Oauth2RegisteredClientDO::getClientIdIssuedAt, entity.getClientIdIssuedAt())
                .eq(StringUtils.hasText(entity.getClientSecret()), Oauth2RegisteredClientDO::getClientSecret, entity.getClientSecret())
                .eq(!ObjectUtils.isEmpty(entity.getClientSecretExpiresAt()), Oauth2RegisteredClientDO::getClientSecretExpiresAt, entity.getClientSecretExpiresAt())
                .eq(StringUtils.hasText(entity.getAutoApprove()), Oauth2RegisteredClientDO::getAutoApprove, entity.getAutoApprove())
                .eq(StringUtils.hasText(entity.getClientName()), Oauth2RegisteredClientDO::getClientName, entity.getClientName())
                .eq(StringUtils.hasText(entity.getClientAuthenticationMethods()), Oauth2RegisteredClientDO::getClientAuthenticationMethods, entity.getClientAuthenticationMethods())
                .eq(StringUtils.hasText(entity.getAuthorizationGrantTypes()), Oauth2RegisteredClientDO::getAuthorizationGrantTypes, entity.getAuthorizationGrantTypes())
                .eq(StringUtils.hasText(entity.getRedirectUris()), Oauth2RegisteredClientDO::getRedirectUris, entity.getRedirectUris())
                .eq(StringUtils.hasText(entity.getPostLogoutRedirectUris()), Oauth2RegisteredClientDO::getPostLogoutRedirectUris, entity.getPostLogoutRedirectUris())
                .eq(StringUtils.hasText(entity.getScopes()), Oauth2RegisteredClientDO::getScopes, entity.getScopes())
                .eq(!ObjectUtils.isEmpty(entity.getAccessTokenValidity()), Oauth2RegisteredClientDO::getAccessTokenValidity, entity.getAccessTokenValidity())
                .eq(!ObjectUtils.isEmpty(entity.getRefreshTokenValidity()), Oauth2RegisteredClientDO::getRefreshTokenValidity, entity.getRefreshTokenValidity())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<Oauth2RegisteredClientDO> lambdaUpdate(Oauth2RegisteredClientDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<Oauth2RegisteredClientDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(Oauth2RegisteredClientDO::getClientId, entity.getClientId())
                .set(Oauth2RegisteredClientDO::getClientIdIssuedAt, entity.getClientIdIssuedAt())
                .set(Oauth2RegisteredClientDO::getClientSecret, entity.getClientSecret())
                .set(Oauth2RegisteredClientDO::getClientSecretExpiresAt, entity.getClientSecretExpiresAt())
                .set(Oauth2RegisteredClientDO::getAutoApprove, entity.getAutoApprove())
                .set(Oauth2RegisteredClientDO::getClientName, entity.getClientName())
                .set(Oauth2RegisteredClientDO::getClientAuthenticationMethods, entity.getClientAuthenticationMethods())
                .set(Oauth2RegisteredClientDO::getAuthorizationGrantTypes, entity.getAuthorizationGrantTypes())
                .set(Oauth2RegisteredClientDO::getRedirectUris, entity.getRedirectUris())
                .set(Oauth2RegisteredClientDO::getPostLogoutRedirectUris, entity.getPostLogoutRedirectUris())
                .set(Oauth2RegisteredClientDO::getScopes, entity.getScopes())
                .set(Oauth2RegisteredClientDO::getAccessTokenValidity, entity.getAccessTokenValidity())
                .set(Oauth2RegisteredClientDO::getRefreshTokenValidity, entity.getRefreshTokenValidity())
        ;
    }


}
