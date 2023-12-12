package com.xht.cloud.system.module.user.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.system.module.user.controller.request.SysUserProfileRequest;
import com.xht.cloud.system.module.user.controller.response.SysUserProfileResponse;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserProfileDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

/**
 * 描述 ：用户信息扩展
 *
 * @author : xht
 **/
@Mapper(componentModel = "spring")
public interface SysUserProfileConvert {

    /**
     * {@link SysUserProfileRequest} to {@link SysUserProfileDO}
     */
    @Named(value = "addRequestToDo")
    SysUserProfileDO toDO(SysUserProfileRequest request);

    /**
     * {@link SysUserProfileDO} to {@link SysUserProfileResponse}
     */
    @Named(value = "DoToResponse")
    SysUserProfileResponse toResponse(SysUserProfileDO testDO);


    /**
     * list转换 {@link SysUserProfileDO} to {@link SysUserProfileResponse}
     */
    @Named(value = "DoToResponseCollection")
    @IterableMapping(qualifiedByName = "DoToResponse")
    List<SysUserProfileResponse> toResponse(List<SysUserProfileDO> testDO);

    /**
     * 分页转换 {@link SysUserProfileDO} to {@link SysUserProfileResponse}
     */
    default PageResponse<SysUserProfileResponse> toPageResponse(IPage<SysUserProfileDO> iPage) {
        if (Objects.nonNull(iPage)) {
            PageResponse<SysUserProfileResponse> pageResponse = PageTool.cloneEmpty(iPage);
            pageResponse.setList(toResponse(iPage.getRecords()));
            return pageResponse;
        }
        return PageTool.empty();
    }

}
