package com.xht.cloud.system.module.area.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoAddRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoQueryRequest;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoUpdateRequest;
import com.xht.cloud.system.module.area.controller.response.SysAreaInfoResponse;
import com.xht.cloud.system.module.area.dao.dataobject.SysAreaInfoDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：地区信息
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysAreaInfoConvert {

    /**
     * {@link SysAreaInfoAddRequest} to {@link SysAreaInfoDO}
     */
    @Named(value = "addRequestToDo")
    SysAreaInfoDO toDO(SysAreaInfoAddRequest addRequest);

    /**
     * {@link SysAreaInfoUpdateRequest} to {@link SysAreaInfoDO}
     */
    @Named(value = "updateRequestToDo")
    SysAreaInfoDO toDO(SysAreaInfoUpdateRequest updateRequest);

    /**
     * {@link SysAreaInfoQueryRequest} to {@link SysAreaInfoDO}
     */
    @Named(value = "queryRequestToDo")
    SysAreaInfoDO toDO(SysAreaInfoQueryRequest queryRequest);

    /**
     * {@link SysAreaInfoDO} to {@link SysAreaInfoResponse}
     */
    @Named(value = "DoToResponse")
    @Mapping(target = "leaf", expression = "java(com.xht.cloud.system.module.area.convert.AreaLeafConvert.convert(testDO.getLeaf()))")
    SysAreaInfoResponse toResponse(SysAreaInfoDO testDO);


    /**
     * list转换 {@link SysAreaInfoDO} to {@link SysAreaInfoResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysAreaInfoResponse> toResponse(List<SysAreaInfoDO> testDO);


    /**
     * 分页转换 {@link SysAreaInfoDO} to {@link SysAreaInfoResponse}
     */
    default PageResponse<SysAreaInfoResponse> toPageResponse(IPage<SysAreaInfoDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysAreaInfoResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
