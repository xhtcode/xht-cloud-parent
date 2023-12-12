package com.xht.cloud.system.module.log.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.log.dao.dataobject.SysLogDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：系统操作日志记录
 *
 * @author : xht
 **/
public final class SysLogWrapper implements EntityWrapper<SysLogDO> {

    /**
     * 私有化构造器
     */
    private SysLogWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysLogWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysLogWrapper wrapper;

        Instance() {
            wrapper = new SysLogWrapper();
        }

        public SysLogWrapper getInstance() {
            return wrapper;
        }
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<SysLogDO> lambdaQuery(SysLogDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysLogDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysLogDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getServerName()), SysLogDO::getServerName, entity.getServerName())
                .eq(StringUtils.hasText(entity.getBusName()), SysLogDO::getBusName, entity.getBusName())
                .eq(StringUtils.hasText(entity.getBusDesc()), SysLogDO::getBusDesc, entity.getBusDesc())
                .eq(StringUtils.hasText(entity.getOperateMethod()), SysLogDO::getOperateMethod, entity.getOperateMethod())
                .eq(!ObjectUtils.isEmpty(entity.getOperateType()), SysLogDO::getOperateType, entity.getOperateType())
                .eq(StringUtils.hasText(entity.getOperateName()), SysLogDO::getOperateName, entity.getOperateName())
                .eq(StringUtils.hasText(entity.getRequestUrl()), SysLogDO::getRequestUrl, entity.getRequestUrl())
                .eq(StringUtils.hasText(entity.getOperateIp()), SysLogDO::getOperateIp, entity.getOperateIp())
                .eq(StringUtils.hasText(entity.getOperateLocation()), SysLogDO::getOperateLocation, entity.getOperateLocation())
                .eq(StringUtils.hasText(entity.getOperateParam()), SysLogDO::getOperateParam, entity.getOperateParam())
                .eq(StringUtils.hasText(entity.getResult()), SysLogDO::getResult, entity.getResult())
                .eq(!ObjectUtils.isEmpty(entity.getStatus()), SysLogDO::getStatus, entity.getStatus())
                .eq(StringUtils.hasText(entity.getErrorMsg()), SysLogDO::getErrorMsg, entity.getErrorMsg())
        ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysLogDO> lambdaUpdate(SysLogDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysLogDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysLogDO::getServerName, entity.getServerName())
                .set(SysLogDO::getBusName, entity.getBusName())
                .set(SysLogDO::getBusDesc, entity.getBusDesc())
                .set(SysLogDO::getOperateMethod, entity.getOperateMethod())
                .set(SysLogDO::getOperateType, entity.getOperateType())
                .set(SysLogDO::getOperateName, entity.getOperateName())
                .set(SysLogDO::getRequestUrl, entity.getRequestUrl())
                .set(SysLogDO::getOperateIp, entity.getOperateIp())
                .set(SysLogDO::getOperateLocation, entity.getOperateLocation())
                .set(SysLogDO::getOperateParam, entity.getOperateParam())
                .set(SysLogDO::getResult, entity.getResult())
                .set(SysLogDO::getStatus, entity.getStatus())
                .set(SysLogDO::getErrorMsg, entity.getErrorMsg())
        ;
    }


}
