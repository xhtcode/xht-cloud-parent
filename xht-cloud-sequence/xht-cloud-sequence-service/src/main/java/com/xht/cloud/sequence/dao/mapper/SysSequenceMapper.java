package com.xht.cloud.sequence.dao.mapper;

import com.xht.cloud.framework.mybatis.mapper.BaseMapperX;
import com.xht.cloud.sequence.dao.dataobject.SysSequenceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述 ：序列生成器
 *
 * @author : 小糊涂
 **/
@Mapper
public interface SysSequenceMapper extends BaseMapperX<SysSequenceDO> {
}
