package com.xht.cloud.sequence.generate.sequence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.sequence.constant.GenerateIdType;
import com.xht.cloud.sequence.controller.request.IdRequest;
import com.xht.cloud.sequence.dao.dataobject.SysSequenceDO;
import com.xht.cloud.sequence.dao.mapper.SysSequenceMapper;
import com.xht.cloud.sequence.exception.SequenceException;
import com.xht.cloud.sequence.generate.GenerateIdHandler;
import com.xht.cloud.sequence.utils.SequenceFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 描述 ：自定义序列生成器
 *
 * @author : 小糊涂
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class GenerateSequenceHandler extends GenerateIdHandler {

    private final SysSequenceMapper sysSequenceMapper;

    /**
     * 生成id
     *
     * @param request {@link IdRequest}
     * @return id
     */
    @Override
    public String generate(IdRequest request) {
        Assert.isFalse(!StringUtils.hasText(request.getSeqCode()), () ->new SequenceException("序列code不能为空!"));
        SysSequenceDO sysSequenceDO = sysSequenceMapper.selectOne(new LambdaQueryWrapper<SysSequenceDO>().eq(SysSequenceDO::getSeqCode, request.getCode()).last(" for update "));
        String format = SequenceFormat.format(sysSequenceDO);
        sysSequenceMapper.update(new LambdaUpdateWrapper<SysSequenceDO>()
                .set(SysSequenceDO::getCurrentValue, sysSequenceDO.getCurrentValue())
                .eq(SysSequenceDO::getId, sysSequenceDO.getId())
        );
        return format;
    }

    /**
     * 生成id
     *
     * @return {@link GenerateIdType}
     */
    @Override
    protected GenerateIdType getType() {
        return GenerateIdType.SEQUENCE;
    }
}
