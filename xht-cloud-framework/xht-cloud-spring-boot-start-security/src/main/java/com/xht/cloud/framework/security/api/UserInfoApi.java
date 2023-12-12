package com.xht.cloud.framework.security.api;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.framework.core.constant.RpcConstants;
import com.xht.cloud.framework.security.dto.UserResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * 描述 ： 用户信息 (RPC 服务)
 *
 * @author : 小糊涂
 **/
@HttpExchange(url = "/rpc-api/sys/user")
public interface UserInfoApi {

    /**
     * 根据用户名查询用户账号
     *
     * @param userName 用户账号
     * @return {@link UserResponseDTO}
     */
    @GetExchange("/{username}")
    R<UserResponseDTO> findUserByUserName(@PathVariable("username") String userName, @RequestHeader(RpcConstants.RPC_HEADER_KEY)String rpcHeaderValue);

}
