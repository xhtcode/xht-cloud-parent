package com.xht.cloud.system.module.dept.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.dept.controller.request.SysPositionAddRequest;
import com.xht.cloud.system.module.dept.controller.request.SysPositionQueryRequest;
import com.xht.cloud.system.module.dept.controller.request.SysPositionUpdateRequest;
import com.xht.cloud.system.module.dept.controller.response.SysPositionResponse;
import com.xht.cloud.system.module.dept.dao.dataobject.SysPositionDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：岗位信息
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysPositionConvert {

    /**
     * {@link SysPositionAddRequest} to {@link SysPositionDO}
     */
    @Named(value = "addRequestToDo")
    SysPositionDO toDO(SysPositionAddRequest addRequest);

    /**
     * {@link SysPositionUpdateRequest} to {@link SysPositionDO}
     */
    @Named(value = "updateRequestToDo")
    SysPositionDO toDO(SysPositionUpdateRequest updateRequest);

    /**
     * {@link SysPositionQueryRequest} to {@link SysPositionDO}
     */
    @Named(value = "queryRequestToDo")
    SysPositionDO toDO(SysPositionQueryRequest queryRequest);

    /**
     * {@link SysPositionDO} to {@link SysPositionResponse}
     */
    @Named(value = "DoToResponse")
    SysPositionResponse toResponse(SysPositionDO testDO);


    /**
     * list转换 {@link SysPositionDO} to {@link SysPositionResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysPositionResponse> toResponse(List<SysPositionDO> testDO);

    /**
     * 分页转换 {@link SysPositionDO} to {@link SysPositionResponse}
     */
    default PageResponse<SysPositionResponse> toPageResponse(IPage<SysPositionDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysPositionResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
