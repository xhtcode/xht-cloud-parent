package com.xht.cloud.system.module.dict.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.dict.controller.request.SysDictAddRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictQueryRequest;
import com.xht.cloud.system.module.dict.controller.request.SysDictUpdateRequest;
import com.xht.cloud.system.module.dict.controller.response.SysDictResponse;
import com.xht.cloud.system.module.dict.controller.response.SysDictVo;
import com.xht.cloud.system.module.dict.dao.dataobject.SysDictDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：字典
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysDictConvert {

    /**
     * {@link SysDictAddRequest} to {@link SysDictDO}
     */
    @Named(value = "addRequestToDo")
    SysDictDO toDO(SysDictAddRequest addRequest);

    /**
     * {@link SysDictUpdateRequest} to {@link SysDictDO}
     */
    @Named(value = "updateRequestToDo")
    SysDictDO toDO(SysDictUpdateRequest updateRequest);

    /**
     * {@link SysDictQueryRequest} to {@link SysDictDO}
     */
    @Named(value = "queryRequestToDo")
    SysDictDO toDO(SysDictQueryRequest queryRequest);

    /**
     * {@link SysDictDO} to {@link SysDictResponse}
     */
    @Named(value = "DoToResponse")
    SysDictResponse toResponse(SysDictDO testDO);


    /**
     * list转换 {@link SysDictDO} to {@link SysDictResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysDictResponse> toResponse(List<SysDictDO> testDO);

    /**
     * 分页转换 {@link SysDictDO} to {@link SysDictResponse}
     */
    default PageResponse<SysDictResponse> toPageResponse(IPage<SysDictDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysDictResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

    /**
     * {@link SysDictDO} to {@link SysDictVo}
     */
    @Named(value = "DoToVo")
    SysDictVo toVo(SysDictDO testDO);
}
