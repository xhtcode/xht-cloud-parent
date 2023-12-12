package com.xht.cloud.system.module.user.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.api.user.dto.response.UserResponseDTO;
import com.xht.cloud.system.module.user.controller.request.SysUserAddRequest;
import com.xht.cloud.system.module.user.controller.request.SysUserQueryRequest;
import com.xht.cloud.system.module.user.controller.request.SysUserUpdateRequest;
import com.xht.cloud.system.module.user.controller.response.SysUserResponse;
import com.xht.cloud.system.module.user.controller.response.SysUserVo;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：用户
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysUserConvert {

    /**
     * {@link SysUserAddRequest} to {@link SysUserDO}
     */
    @Named(value = "requestToDo")
    SysUserDO toDO(SysUserAddRequest addRequest);

    /**
     * {@link SysUserUpdateRequest} to {@link SysUserDO}
     */
    @Named(value = "updateRequestToDo")
    SysUserDO toDO(SysUserUpdateRequest updateRequest);

    /**
     * {@link SysUserQueryRequest} to {@link SysUserDO}
     */
    @Named(value = "queryRequestToDo")
    SysUserDO toDO(SysUserQueryRequest queryRequest);

    /**
     * {@link SysUserDO} to {@link SysUserResponse}
     */
    @Named(value = "DoToResponse")
    SysUserResponse toResponse(SysUserDO testDO);


    /**
     * list转换 {@link SysUserDO} to {@link SysUserResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysUserResponse> toResponse(List<SysUserDO> testDO);

    /**
     * 分页转换 {@link SysUserDO} to {@link SysUserResponse}
     */
    default PageResponse<SysUserResponse> toPageResponse(IPage<SysUserDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysUserResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

    default UserResponseDTO toDTO(SysUserVo sysUserVo) {
        if (Objects.isNull(sysUserVo)) {
            return null;
        }
        SysUserResponse sysUser = sysUserVo.getSysUser();
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(sysUser.getId());
        responseDTO.setNickName(sysUser.getNickName());
        responseDTO.setUserName(sysUser.getUserName());
        responseDTO.setPassWord(sysUser.getPassWord());
        responseDTO.setPassWordSalt(sysUser.getPassWordSalt());
        responseDTO.setUserType(sysUser.getUserType());
        responseDTO.setQqOpenid(sysUser.getQqOpenid());
        responseDTO.setWxOpenid(sysUser.getWxOpenid());
        responseDTO.setWxUnionid(sysUser.getWxOpenid());
        responseDTO.setIsActive(sysUser.getIsActive());
        responseDTO.setIsAdmin(sysUser.getIsAdmin());
        responseDTO.setRegisteredTime(sysUser.getRegisteredTime());
        responseDTO.setLastLoginTime(sysUser.getLastLoginTime());
        responseDTO.setRoleCode(sysUserVo.getRoleCode());
        responseDTO.setDataScope(sysUserVo.getDataScope());
        responseDTO.setDeptId(sysUser.getDeptId());
        return responseDTO;
    }
}
