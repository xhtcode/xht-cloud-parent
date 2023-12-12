package com.xht.cloud.system.module.config.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.config.controller.request.SysConfigAddRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigQueryRequest;
import com.xht.cloud.system.module.config.controller.request.SysConfigUpdateRequest;
import com.xht.cloud.system.module.config.controller.response.SysConfigResponse;
import com.xht.cloud.system.module.config.dao.dataobject.SysConfigDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：系统配置信息
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysConfigConvert {

    /**
     * {@link SysConfigAddRequest} to {@link SysConfigDO}
     */
    @Named(value = "addRequestToDo")
    SysConfigDO toDO(SysConfigAddRequest addRequest);

    /**
     * {@link SysConfigUpdateRequest} to {@link SysConfigDO}
     */
    @Named(value = "updateRequestToDo")
    SysConfigDO toDO(SysConfigUpdateRequest updateRequest);

    /**
     * {@link SysConfigQueryRequest} to {@link SysConfigDO}
     */
    @Named(value = "queryRequestToDo")
    SysConfigDO toDO(SysConfigQueryRequest queryRequest);

    /**
     * {@link SysConfigDO} to {@link SysConfigResponse}
     */
    @Named(value = "DoToResponse")
    SysConfigResponse toResponse(SysConfigDO testDO);


    /**
     * list转换 {@link SysConfigDO} to {@link SysConfigResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysConfigResponse> toResponse(List<SysConfigDO> testDO);

    /**
     * 分页转换 {@link SysConfigDO} to {@link SysConfigResponse}
     */
    default PageResponse<SysConfigResponse> toPageResponse(IPage<SysConfigDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysConfigResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
