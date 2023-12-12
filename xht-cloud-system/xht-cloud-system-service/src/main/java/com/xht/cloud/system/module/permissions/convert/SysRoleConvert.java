package com.xht.cloud.system.module.permissions.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.api.permissions.RoleResponseDTO;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleAddRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleQueryRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysRoleUpdateRequest;
import com.xht.cloud.system.module.permissions.controller.response.SysRoleResponse;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：系统角色表
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysRoleConvert {

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    /**
     * {@link SysRoleAddRequest} to {@link SysRoleDO}
     */
    @Named(value = "addRequestToDo")
    SysRoleDO toDO(SysRoleAddRequest addRequest);

    /**
     * {@link SysRoleUpdateRequest} to {@link SysRoleDO}
     */
    @Named(value = "updateRequestToDo")
    SysRoleDO toDO(SysRoleUpdateRequest updateRequest);

    /**
     * {@link SysRoleQueryRequest} to {@link SysRoleDO}
     */
    @Named(value = "queryRequestToDo")
    SysRoleDO toDO(SysRoleQueryRequest queryRequest);

    /**
     * {@link SysRoleDO} to {@link SysRoleResponse}
     */
    @Named(value = "DoToResponse")
    SysRoleResponse toResponse(SysRoleDO testDO);


    /**
     * list转换 {@link SysRoleDO} to {@link SysRoleResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysRoleResponse> toResponse(List<SysRoleDO> testDO);

    /**
     * 分页转换 {@link SysRoleDO} to {@link SysRoleResponse}
     */
    default PageResponse<SysRoleResponse> toPageResponse(IPage<SysRoleDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysRoleResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

    @Named(value = "DoToDto")
    RoleResponseDTO toDTO(SysRoleResponse sysRoleResponse);


    @Named(value = "DoToDtoCollection")
    @IterableMapping(qualifiedByName = "DoToDto")
    List<RoleResponseDTO> toDTO(List<SysRoleResponse> sysRoleResponse);
}
