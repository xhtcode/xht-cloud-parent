package com.xht.cloud.system.api.user;

import com.xht.cloud.framework.core.api.R;
import com.xht.cloud.system.api.ApiConstants;
import com.xht.cloud.system.api.user.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 描述 ： 用户信息 (RPC 服务)
 *
 * @author : 小糊涂
 **/
@Tag(name = "RPC 服务 - 用户信息")
@FeignClient(value = ApiConstants.SERVER_NAME, contextId = "userFeignApi")
public interface UserFeignApi {

    /**
     * url 前缀地址
     */
    String PREFIX = ApiConstants.PREFIX + "/user";

    /**
     * 根据用户名查询用户账号
     *
     * @param userName 用户账号
     * @return {@link UserResponseDTO}
     */
    @GetMapping(PREFIX + "/{username}")
    R<UserResponseDTO> findUserByUserName(@PathVariable("username") String userName);

}
