package com.xht.cloud.system.module.dict.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictItemUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictItemResponse;
import com.xht.cloud.system.module.dict.dao.dataobject.SysDictItemDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：字典数据
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysDictItemConvert {

    /**
     * {@link SysDictItemAddRequest} to {@link SysDictItemDO}
     */
    @Named(value = "addRequestToDo")
    SysDictItemDO toDO(SysDictItemAddRequest addRequest);

    /**
     * {@link SysDictItemUpdateRequest} to {@link SysDictItemDO}
     */
    @Named(value = "updateRequestToDo")
    SysDictItemDO toDO(SysDictItemUpdateRequest updateRequest);

    /**
     * {@link SysDictItemQueryRequest} to {@link SysDictItemDO}
     */
    @Named(value = "queryRequestToDo")
    SysDictItemDO toDO(SysDictItemQueryRequest queryRequest);

    /**
     * {@link SysDictItemDO} to {@link SysDictItemResponse}
     */
    @Named(value = "DoToResponse")
    SysDictItemResponse toResponse(SysDictItemDO testDO);


    /**
     * list转换 {@link SysDictItemDO} to {@link SysDictItemResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysDictItemResponse> toResponse(List<SysDictItemDO> testDO);

    /**
     * 分页转换 {@link SysDictItemDO} to {@link SysDictItemResponse}
     */
    default PageResponse<SysDictItemResponse> toPageResponse(IPage<SysDictItemDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysDictItemResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
