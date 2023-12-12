package com.xht.cloud.system.module.poetry.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryAddRequest;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryQueryRequest;
import com.xht.cloud.system.module.poetry.controller.request.SysDictPoetryUpdateRequest;
import com.xht.cloud.system.module.poetry.controller.response.SysDictPoetryResponse;
import com.xht.cloud.system.module.poetry.dao.dataobject.SysDictPoetryDO;
import java.util.List;
import java.util.Objects;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * 描述 ：
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysDictPoetryConvert {

    /**
     * {@link SysDictPoetryAddRequest} to {@link SysDictPoetryDO}
     */
    @Named(value = "addRequestToDo")
    SysDictPoetryDO toDO(SysDictPoetryAddRequest addRequest);

    /**
     * {@link SysDictPoetryUpdateRequest} to {@link SysDictPoetryDO}
     */
    @Named(value = "updateRequestToDo")
    SysDictPoetryDO toDO(SysDictPoetryUpdateRequest updateRequest);

    /**
     * {@link SysDictPoetryQueryRequest} to {@link SysDictPoetryDO}
     */
    @Named(value = "queryRequestToDo")
    SysDictPoetryDO toDO(SysDictPoetryQueryRequest queryRequest);

    /**
     * {@link SysDictPoetryDO} to {@link SysDictPoetryResponse}
     */
    @Named(value = "DoToResponse")
    SysDictPoetryResponse toResponse(SysDictPoetryDO testDO);


    /**
     * list转换 {@link SysDictPoetryDO} to {@link SysDictPoetryResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysDictPoetryResponse> toResponse(List<SysDictPoetryDO> testDO);

    /**
     * 分页转换 {@link SysDictPoetryDO} to {@link SysDictPoetryResponse}
     */
    default PageResponse<SysDictPoetryResponse> toPageResponse(IPage<SysDictPoetryDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysDictPoetryResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
