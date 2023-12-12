package com.xht.cloud.system.module.dept.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.dept.controller.request.SysDeptAddRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptQueryRequest;
import com.xht.cloud.system.module.dept.controller.request.SysDeptUpdateRequest;
import com.xht.cloud.system.module.dept.controller.response.SysDeptResponse;
import com.xht.cloud.system.module.dept.dao.dataobject.SysDeptDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：部门
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysDeptConvert {

    /**
     * {@link SysDeptAddRequest} to {@link SysDeptDO}
     */
    @Named(value = "addRequestToDo")
    SysDeptDO toDO(SysDeptAddRequest addRequest);

    /**
     * {@link SysDeptUpdateRequest} to {@link SysDeptDO}
     */
    @Named(value = "updateRequestToDo")
    SysDeptDO toDO(SysDeptUpdateRequest updateRequest);

    /**
     * {@link SysDeptQueryRequest} to {@link SysDeptDO}
     */
    @Named(value = "queryRequestToDo")
    SysDeptDO toDO(SysDeptQueryRequest queryRequest);

    /**
     * {@link SysDeptDO} to {@link SysDeptResponse}
     */
    @Named(value = "DoToResponse")
    SysDeptResponse toResponse(SysDeptDO testDO);


    /**
     * list转换 {@link SysDeptDO} to {@link SysDeptResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysDeptResponse> toResponse(List<SysDeptDO> testDO);

    /**
     * 分页转换 {@link SysDeptDO} to {@link SysDeptResponse}
     */
    default PageResponse<SysDeptResponse> toPageResponse(IPage<SysDeptDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysDeptResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
