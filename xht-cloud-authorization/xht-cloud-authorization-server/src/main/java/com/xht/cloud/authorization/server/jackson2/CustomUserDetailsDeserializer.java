package com.xht.cloud.authorization.server.jackson2;

import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xht.cloud.framework.core.json.JsonNodeUtils;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Set;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
public class CustomUserDetailsDeserializer extends JsonDeserializer<CustomUserDetails> {
    private static final TypeReference<Set<GrantedAuthority>> HERODOTUS_GRANTED_AUTHORITY_SET = new TypeReference<Set<GrantedAuthority>>() {
    };
    private static final TypeReference<Set<String>> HERODOTUS_ROLE_SET = new TypeReference<Set<String>>() {
    };

    @Override
    public CustomUserDetails deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Set<? extends GrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"), HERODOTUS_GRANTED_AUTHORITY_SET);
        Set<String> roleCode = mapper.convertValue(jsonNode.get("roleCode"), HERODOTUS_ROLE_SET);
        JsonNode passwordNode = JsonNodeUtils.readJsonNode(jsonNode, "password");
        String userId = JsonNodeUtils.findStringValue(jsonNode, "userId");
        String username = JsonNodeUtils.findStringValue(jsonNode, "username");
        String password = passwordNode.asText("");
        boolean enabled = JsonNodeUtils.findBooleanValue(jsonNode, "enabled");
        String userType = JsonNodeUtils.findStringValue(jsonNode, "userType");
        String deptId = JsonNodeUtils.findStringValue(jsonNode, "deptId");
        String dataScope = JsonNodeUtils.findStringValue(jsonNode, "dataScope");
        CustomUserDetails result = new CustomUserDetails(username, password, authorities);
        result.setUserId(userId);
        result.setEnabled(enabled);
        result.setRoleCode(roleCode);
        result.setUserType(userType);
        result.setDeptId(deptId);
        result.setDataScope(Convert.toInt(dataScope, null));
        if (passwordNode.asText(null) == null) {
            result.eraseCredentials();
        }
        return result;
    }
}
