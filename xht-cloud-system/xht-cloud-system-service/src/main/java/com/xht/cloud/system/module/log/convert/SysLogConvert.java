package com.xht.cloud.system.module.log.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.log.controller.request.SysLogAddRequest;
import com.xht.cloud.system.module.log.controller.request.SysLogQueryRequest;
import com.xht.cloud.system.module.log.controller.request.SysLogUpdateRequest;
import com.xht.cloud.system.module.log.controller.response.SysLogResponse;
import com.xht.cloud.system.module.log.dao.dataobject.SysLogDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：系统操作日志记录
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysLogConvert {

    /**
     * {@link SysLogAddRequest} to {@link SysLogDO}
     */
    @Named(value = "addRequestToDo")
    SysLogDO toDO(SysLogAddRequest addRequest);

    /**
     * {@link SysLogUpdateRequest} to {@link SysLogDO}
     */
    @Named(value = "updateRequestToDo")
    SysLogDO toDO(SysLogUpdateRequest updateRequest);

    /**
     * {@link SysLogQueryRequest} to {@link SysLogDO}
     */
    @Named(value = "queryRequestToDo")
    SysLogDO toDO(SysLogQueryRequest queryRequest);

    /**
     * {@link SysLogDO} to {@link SysLogResponse}
     */
    @Named(value = "DoToResponse")
    SysLogResponse toResponse(SysLogDO testDO);


    /**
     * list转换 {@link SysLogDO} to {@link SysLogResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysLogResponse> toResponse(List<SysLogDO> testDO);

    /**
     * 分页转换 {@link SysLogDO} to {@link SysLogResponse}
     */
    default PageResponse<SysLogResponse> toPageResponse(IPage<SysLogDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysLogResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
