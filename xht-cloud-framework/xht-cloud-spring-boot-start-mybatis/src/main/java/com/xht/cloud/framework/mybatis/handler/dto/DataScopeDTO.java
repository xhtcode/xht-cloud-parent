package com.xht.cloud.framework.mybatis.handler.dto;

import com.xht.cloud.framework.mybatis.core.enums.DeptUserDataScopeEnum;
import com.xht.cloud.framework.security.core.userdetails.CustomUserDetails;
import lombok.Builder;
import lombok.Getter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Getter
@Builder
public class DataScopeDTO {

    /**
     * 如果返回false 那么就不会拼装sql数据 用户最大数据权限使用
     */
    private boolean verify;

    private String deptId;

    private String userId;

    private CustomUserDetails customUserDetails;

    private DeptUserDataScopeEnum dataScope;
}
