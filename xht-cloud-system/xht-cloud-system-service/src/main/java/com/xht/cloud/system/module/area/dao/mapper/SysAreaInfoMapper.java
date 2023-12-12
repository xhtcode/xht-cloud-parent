package com.xht.cloud.system.module.area.dao.mapper;

import com.xht.cloud.framework.mybatis.mapper.BaseMapperX;
import com.xht.cloud.system.module.area.controller.request.SysAreaInfoQueryRequest;
import com.xht.cloud.system.module.area.dao.dataobject.SysAreaInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述 ：地区信息
 *
 * @author : xht
 **/
@Mapper
public interface SysAreaInfoMapper extends BaseMapperX<SysAreaInfoDO> {

    List<SysAreaInfoDO> selectListByRequest(SysAreaInfoQueryRequest queryRequest);
}
