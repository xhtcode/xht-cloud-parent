package com.xht.cloud.framework.log.event;

import com.xht.cloud.framework.log.dto.SysLogDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 描述 ：系统日志-事件
 *
 * @author : 小糊涂
 **/
@Getter
public class SysLogEvent extends ApplicationEvent {
    private final SysLogDTO dto;

    public SysLogEvent(SysLogDTO dto) {
        super(dto);
        this.dto = dto;
    }

}
