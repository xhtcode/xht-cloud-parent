package com.xht.cloud.authorization.server.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xht.cloud.authorization.server.dataobject.AuthorizationDO;
import com.xht.cloud.authorization.server.jackson2.OAuth2JacksonProcessor;
import com.xht.cloud.authorization.server.repository.AuthorizationRepository;
import com.xht.cloud.authorization.server.service.AuthorizationService;
import com.xht.cloud.authorization.server.utils.OAuth2AuthorizationUtils;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.core.support.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2DeviceCode;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.OAuth2UserCode;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationRepository authorizationRepository;
    private final RegisteredClientRepository registeredClientRepository;
    private final OAuth2JacksonProcessor processor = new OAuth2JacksonProcessor();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OAuth2Authorization authorization) {
        AuthorizationDO entity = convert(authorization);
        if (Objects.isNull(entity)) {
            throw new RuntimeException("entity is null !");
        }
        authorizationRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        this.authorizationRepository.deleteById(authorization.getId());
    }

    @Override
    public OAuth2Authorization findByState(String state) {
        Assert.isFalse(!StringUtils.hasText(state), "state is null!");
        return convert(authorizationRepository.findByState(state).orElse(null));
    }

    @Override
    public OAuth2Authorization findByAuthorizationCodeValue(String authorizationCode) {
        Assert.isFalse(!StringUtils.hasText(authorizationCode), "state is null!");
        return convert(authorizationRepository.findByAuthorizationCodeValue(authorizationCode).orElse(null));
    }

    @Override
    public OAuth2Authorization findByAccessTokenValue(String accessToken) {
        Assert.isFalse(!StringUtils.hasText(accessToken), "accessToken is null!");
        return convert(authorizationRepository.findByAccessTokenValue(accessToken).orElse(null));
    }

    @Override
    public OAuth2Authorization findByRefreshTokenValue(String refreshToken) {
        Assert.isFalse(!StringUtils.hasText(refreshToken), "refreshToken is null!");
        return convert(authorizationRepository.findByRefreshTokenValue(refreshToken).orElse(null));
    }

    @Override
    public OAuth2Authorization findByOidcIdTokenValue(String idToken) {
        Assert.isFalse(!StringUtils.hasText(idToken), "idToken is null!");
        return convert(authorizationRepository.findByOidcIdTokenValue(idToken).orElse(null));
    }

    @Override
    public OAuth2Authorization findByUserCodeValue(String userCode) {
        Assert.isFalse(!StringUtils.hasText(userCode), "userCode is null!");
        return convert(authorizationRepository.findByUserCodeValue(userCode).orElse(null));
    }

    @Override
    public OAuth2Authorization findByDeviceCodeValue(String deviceCode) {
        Assert.isFalse(!StringUtils.hasText(deviceCode), "deviceCode is null!");
        return convert(authorizationRepository.findByDeviceCodeValue(deviceCode).orElse(null));
    }

    @Override
    public OAuth2Authorization findByAnyTokenType(String token) {
        Assert.isFalse(!StringUtils.hasText(token), "token is null!");
        return convert(authorizationRepository.findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue(token).orElse(null));
    }

    @Override
    public OAuth2Authorization findById(String id) {
        Assert.isFalse(!StringUtils.hasText(id), "id is null!");
        return convert(authorizationRepository.findById(id).orElse(null));
    }

    @Override
    public AuthorizationDO convert(OAuth2Authorization authorization) {
        AuthorizationDO entity = new AuthorizationDO();
        entity.setId(authorization.getId());
        entity.setRegisteredClientId(authorization.getRegisteredClientId());
        entity.setPrincipalName(authorization.getPrincipalName());
        entity.setAuthorizationGrantType(authorization.getAuthorizationGrantType().getValue());
        entity.setAuthorizedScopes(org.springframework.util.StringUtils.collectionToDelimitedString(authorization.getAuthorizedScopes(), ","));
        entity.setAttributes(processor.writeMap(authorization.getAttributes()));
        entity.setState(authorization.getAttribute(OAuth2ParameterNames.STATE));

        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode =
                authorization.getToken(OAuth2AuthorizationCode.class);
        setTokenValues(
                authorizationCode,
                entity::setAuthorizationCodeValue,
                entity::setAuthorizationCodeIssuedAt,
                entity::setAuthorizationCodeExpiresAt,
                entity::setAuthorizationCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2AccessToken> accessToken =
                authorization.getToken(OAuth2AccessToken.class);
        setTokenValues(
                accessToken,
                entity::setAccessTokenValue,
                entity::setAccessTokenIssuedAt,
                entity::setAccessTokenExpiresAt,
                entity::setAccessTokenMetadata
        );
        if (accessToken != null && accessToken.getToken().getScopes() != null) {
            entity.setAccessTokenScopes(StringUtils.collectionToDelimitedString(accessToken.getToken().getScopes(), ","));
        }

        entity.setAccessTokenType("");
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken =
                authorization.getToken(OAuth2RefreshToken.class);
        setTokenValues(
                refreshToken,
                entity::setRefreshTokenValue,
                entity::setRefreshTokenIssuedAt,
                entity::setRefreshTokenExpiresAt,
                entity::setRefreshTokenMetadata
        );


        OAuth2Authorization.Token<OidcIdToken> oidcIdToken =
                authorization.getToken(OidcIdToken.class);
        setTokenValues(
                oidcIdToken,
                entity::setOidcIdTokenValue,
                entity::setOidcIdTokenIssuedAt,
                entity::setOidcIdTokenExpiresAt,
                entity::setOidcIdTokenMetadata
        );
        if (oidcIdToken != null) {
            entity.setOidcIdTokenClaims(processor.writeMap(oidcIdToken.getClaims()));
        }

        OAuth2Authorization.Token<OAuth2UserCode> userCode =
                authorization.getToken(OAuth2UserCode.class);
        setTokenValues(
                userCode,
                entity::setUserCodeValue,
                entity::setUserCodeIssuedAt,
                entity::setUserCodeExpiresAt,
                entity::setUserCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCode =
                authorization.getToken(OAuth2DeviceCode.class);
        setTokenValues(
                deviceCode,
                entity::setDeviceCodeValue,
                entity::setDeviceCodeIssuedAt,
                entity::setDeviceCodeExpiresAt,
                entity::setDeviceCodeMetadata
        );

        return entity;
    }


    private void setTokenValues(
            OAuth2Authorization.Token<?> token,
            Consumer<String> tokenValueConsumer,
            Consumer<LocalDateTime> issuedAtConsumer,
            Consumer<LocalDateTime> expiresAtConsumer,
            Consumer<String> metadataConsumer) {
        if (token != null) {
            OAuth2Token oAuth2Token = token.getToken();
            tokenValueConsumer.accept(oAuth2Token.getTokenValue());
            issuedAtConsumer.accept(DateUtil.toLocalDateTime(oAuth2Token.getIssuedAt()));
            expiresAtConsumer.accept(DateUtil.toLocalDateTime(oAuth2Token.getExpiresAt()));
            metadataConsumer.accept(processor.writeMap(token.getMetadata()));
        }
    }

    @Override
    public OAuth2Authorization convert(AuthorizationDO entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        RegisteredClient registeredClient = registeredClientRepository.findById(entity.getRegisteredClientId());
        if (Objects.isNull(registeredClient)) {
            throw new DataRetrievalFailureException("The RegisteredClient with id '" + entity.getRegisteredClientId() + "' was not found in the RegisteredClientRepository.");
        }
        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .id(entity.getId())
                .principalName(entity.getPrincipalName())
                .authorizationGrantType(OAuth2AuthorizationUtils.resolveAuthorizationGrantType(entity.getAuthorizationGrantType()))
                .authorizedScopes(StringUtils.commaDelimitedListToSet(entity.getAuthorizedScopes()))
                .attributes(attributes -> attributes.putAll(processor.parseMap(entity.getAttributes())));
        if (Objects.nonNull(entity.getState())) {
            builder.attribute(OAuth2ParameterNames.STATE, entity.getState());
        }

        if (entity.getAuthorizationCodeValue() != null) {
            OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                    entity.getAuthorizationCodeValue(),
                    DateUtil.toInstant(entity.getAuthorizationCodeIssuedAt()),
                    DateUtil.toInstant(entity.getAuthorizationCodeExpiresAt()));
            builder.token(authorizationCode, metadata -> metadata.putAll(processor.parseMap(entity.getAuthorizationCodeMetadata())));
        }

        if (entity.getAccessTokenValue() != null) {
            OAuth2AccessToken accessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.BEARER,
                    entity.getAccessTokenValue(),
                    DateUtil.toInstant(entity.getAccessTokenIssuedAt()),
                    DateUtil.toInstant(entity.getAccessTokenExpiresAt()),
                    org.springframework.util.StringUtils.commaDelimitedListToSet(entity.getAccessTokenScopes()));
            builder.token(accessToken, metadata -> metadata.putAll(processor.parseMap(entity.getAccessTokenMetadata())));
        }

        if (entity.getRefreshTokenValue() != null) {
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    entity.getRefreshTokenValue(),
                    DateUtil.toInstant(entity.getRefreshTokenIssuedAt()),
                    DateUtil.toInstant(entity.getRefreshTokenExpiresAt()));
            builder.token(refreshToken, metadata -> metadata.putAll(processor.parseMap(entity.getRefreshTokenMetadata())));
        }

        if (entity.getOidcIdTokenValue() != null) {
            OidcIdToken idToken = new OidcIdToken(
                    entity.getOidcIdTokenValue(),
                    DateUtil.toInstant(entity.getOidcIdTokenIssuedAt()),
                    DateUtil.toInstant(entity.getOidcIdTokenExpiresAt()),
                    processor.parseMap(entity.getOidcIdTokenClaims()));
            builder.token(idToken, metadata -> metadata.putAll(processor.parseMap(entity.getOidcIdTokenMetadata())));
        }

        if (entity.getUserCodeValue() != null) {
            OAuth2UserCode userCode = new OAuth2UserCode(
                    entity.getUserCodeValue(),
                    DateUtil.toInstant(entity.getUserCodeIssuedAt()),
                    DateUtil.toInstant(entity.getUserCodeExpiresAt()));
            builder.token(userCode, metadata -> metadata.putAll(processor.parseMap(entity.getUserCodeMetadata())));
        }

        if (entity.getDeviceCodeValue() != null) {
            OAuth2DeviceCode deviceCode = new OAuth2DeviceCode(
                    entity.getDeviceCodeValue(),
                    DateUtil.toInstant(entity.getDeviceCodeIssuedAt()),
                    DateUtil.toInstant(entity.getDeviceCodeExpiresAt()));
            builder.token(deviceCode, metadata -> metadata.putAll(processor.parseMap(entity.getDeviceCodeMetadata())));
        }

        return builder.build();
    }

}
