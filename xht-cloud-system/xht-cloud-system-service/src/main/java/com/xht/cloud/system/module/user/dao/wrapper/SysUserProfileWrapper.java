package com.xht.cloud.system.module.user.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserProfileDO;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：用户信息扩展表
 *
 * @author : xht
 **/
public final class SysUserProfileWrapper implements EntityWrapper<SysUserProfileDO> {

    /**
     * 私有化构造器
     */
    private SysUserProfileWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysUserProfileWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 获取 {@link LambdaQueryWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaQueryWrapper}
     */
    @Override
    public LambdaQueryWrapper<SysUserProfileDO> lambdaQuery(SysUserProfileDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysUserProfileDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysUserProfileDO::getId, entity.getId())
                .eq(StringUtils.hasText(entity.getUserId()), SysUserProfileDO::getUserId, entity.getUserId())
                .eq(StringUtils.hasText(entity.getRealName()), SysUserProfileDO::getRealName, entity.getRealName())
                .eq(StringUtils.hasText(entity.getGender()), SysUserProfileDO::getGender, entity.getGender())
                .eq(StringUtils.hasText(entity.getIdCardNumber()), SysUserProfileDO::getIdCardNumber, entity.getIdCardNumber())
                .eq(StringUtils.hasText(entity.getPhoneNumber()), SysUserProfileDO::getPhoneNumber, entity.getPhoneNumber())
                .eq(StringUtils.hasText(entity.getEmail()), SysUserProfileDO::getEmail, entity.getEmail())
                .eq(!ObjectUtils.isEmpty(entity.getBirthday()), SysUserProfileDO::getBirthday, entity.getBirthday())
                .eq(StringUtils.hasText(entity.getAddress()), SysUserProfileDO::getAddress, entity.getAddress())
                .eq(StringUtils.hasText(entity.getDescription()), SysUserProfileDO::getDescription, entity.getDescription())
                ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysUserProfileDO> lambdaUpdate(SysUserProfileDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysUserProfileDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysUserProfileDO::getUserId, entity.getUserId())
                .set(SysUserProfileDO::getRealName, entity.getRealName())
                .set(SysUserProfileDO::getGender, entity.getGender())
                .set(SysUserProfileDO::getIdCardNumber, entity.getIdCardNumber())
                .set(SysUserProfileDO::getPhoneNumber, entity.getPhoneNumber())
                .set(SysUserProfileDO::getEmail, entity.getEmail())
                .set(SysUserProfileDO::getBirthday, entity.getBirthday())
                .set(SysUserProfileDO::getAddress, entity.getAddress())
                .set(SysUserProfileDO::getAddressDetailed, entity.getAddressDetailed())
                .set(SysUserProfileDO::getDescription, entity.getDescription())
                ;
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysUserProfileWrapper wrapper;

        Instance() {
            wrapper = new SysUserProfileWrapper();
        }

        public SysUserProfileWrapper getInstance() {
            return wrapper;
        }
    }


}
