package com.xht.cloud.system.module.permissions.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuAddRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuQueryRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuRequest;
import com.xht.cloud.system.module.permissions.controller.request.SysMenuUpdateRequest;
import com.xht.cloud.system.module.permissions.controller.response.SysMenuResponse;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：菜单权限
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysMenuConvert {

    /**
     * {@link SysMenuAddRequest} to {@link SysMenuDO}
     */
    @Named(value = "addRequestRequestToDo")
    SysMenuDO toDO(SysMenuAddRequest addRequest);

    /**
     * {@link SysMenuUpdateRequest} to {@link SysMenuDO}
     */
    @Named(value = "updateRequestToDo")
    SysMenuDO toDO(SysMenuUpdateRequest updateRequest);

    /**
     * {@link SysMenuQueryRequest} to {@link SysMenuDO}
     */
    @Named(value = "queryRequestToDo")
    SysMenuDO toDO(SysMenuQueryRequest queryRequest);

    /**
     * {@link SysMenuDO} to {@link SysMenuResponse}
     */
    @Named(value = "DoToResponse")
    SysMenuResponse toResponse(SysMenuDO testDO);


    /**
     * list转换 {@link SysMenuDO} to {@link SysMenuResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysMenuResponse> toResponse(List<SysMenuDO> testDO);

    /**
     * 分页转换 {@link SysMenuDO} to {@link SysMenuResponse}
     */
    default PageResponse<SysMenuResponse> toPageResponse(IPage<SysMenuDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysMenuResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

    /**
     * {@link SysMenuRequest} to {@link SysMenuDO}
     */
    SysMenuDO toDO(SysMenuRequest menuRequest);
}
