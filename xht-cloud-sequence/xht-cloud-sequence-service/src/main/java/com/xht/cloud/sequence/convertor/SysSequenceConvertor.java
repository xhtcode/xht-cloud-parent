package com.xht.cloud.sequence.convertor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.sequence.controller.request.SysSequenceCreateRequest;
import com.xht.cloud.sequence.controller.request.SysSequenceQueryRequest;
import com.xht.cloud.sequence.controller.request.SysSequenceUpdateRequest;
import com.xht.cloud.sequence.controller.response.SysSequenceResponse;
import com.xht.cloud.sequence.dao.dataobject.SysSequenceDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Mapper(componentModel = "spring")
public interface SysSequenceConvertor {


    /**
     * {@link SysSequenceCreateRequest} to {@link SysSequenceDO}
     */
    @Named(value = "addRequestToDo")
    SysSequenceDO toDO(SysSequenceCreateRequest addRequest);

    /**
     * {@link SysSequenceUpdateRequest} to {@link SysSequenceDO}
     */
    @Named(value = "updateRequestToDo")
    SysSequenceDO toDO(SysSequenceUpdateRequest updateRequest);

    /**
     * {@link SysSequenceQueryRequest} to {@link SysSequenceDO}
     */
    @Named(value = "queryRequestToDo")
    SysSequenceDO toDO(SysSequenceQueryRequest queryRequest);

    /**
     * {@link SysSequenceDO} to {@link SysSequenceResponse}
     */
    @Named(value = "DoToResponse")
    SysSequenceResponse toResponse(SysSequenceDO testDO);


    /**
     * list转换 {@link SysSequenceDO} to {@link SysSequenceResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysSequenceResponse> toResponse(List<SysSequenceDO> testDO);

    /**
     * 分页转换 {@link SysSequenceDO} to {@link SysSequenceResponse}
     */
    default PageResponse<SysSequenceResponse> toPageResponse(IPage<SysSequenceDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysSequenceResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
