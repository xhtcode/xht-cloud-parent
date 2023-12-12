package com.xht.cloud.system.module.user.dao.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.mybatis.wrapper.EntityWrapper;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserDO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 描述 ：用户表
 *
 * @author : xht
 **/
public final class SysUserWrapper implements EntityWrapper<SysUserDO> {

    /**
     * 私有化构造器
     */
    private SysUserWrapper() {
    }

    /**
     * 获取实例
     */
    public static SysUserWrapper getInstance() {
        return Instance.INSTANCE.getInstance();
    }

    /**
     * 实例处理化
     */
    private enum Instance {

        INSTANCE;

        private final SysUserWrapper wrapper;

        Instance() {
            wrapper = new SysUserWrapper();
        }

        public SysUserWrapper getInstance() {
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
    public LambdaQueryWrapper<SysUserDO> lambdaQuery(SysUserDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaQuery();
        }
        LambdaQueryWrapper<SysUserDO> wrapper = new LambdaQueryWrapper<>();
        return wrapper
                .eq(StringUtils.hasText(entity.getId()), SysUserDO::getId, entity.getId())
                .like(StringUtils.hasText(entity.getNickName()), SysUserDO::getNickName, entity.getNickName())
                .like(StringUtils.hasText(entity.getUserName()), SysUserDO::getUserName, entity.getUserName())
                .eq(StringUtils.hasText(entity.getPassWord()), SysUserDO::getPassWord, entity.getPassWord())
                .eq(StringUtils.hasText(entity.getPassWordSalt()), SysUserDO::getPassWordSalt, entity.getPassWordSalt())
                .eq(StringUtils.hasText(entity.getPassWordOld()), SysUserDO::getPassWordOld, entity.getPassWordOld())
                .eq(StringUtils.hasText(entity.getPassWordSaltOld()), SysUserDO::getPassWordSaltOld, entity.getPassWordSaltOld())
                .eq(StringUtils.hasText(entity.getDeptId()), SysUserDO::getDeptId, entity.getDeptId())
                .eq(StringUtils.hasText(entity.getUserAvatar()), SysUserDO::getUserAvatar, entity.getUserAvatar())
                .eq(StringUtils.hasText(entity.getUserType()), SysUserDO::getUserType, entity.getUserType())
                .eq(StringUtils.hasText(entity.getQqOpenid()), SysUserDO::getQqOpenid, entity.getQqOpenid())
                .eq(StringUtils.hasText(entity.getWxOpenid()), SysUserDO::getWxOpenid, entity.getWxOpenid())
                .eq(StringUtils.hasText(entity.getWxUnionid()), SysUserDO::getWxUnionid, entity.getWxUnionid())
                .eq(StringUtils.hasText(entity.getIsLock()), SysUserDO::getIsLock, entity.getIsLock())
                .eq(StringUtils.hasText(entity.getIsActive()), SysUserDO::getIsActive, entity.getIsActive())
                .eq(StringUtils.hasText(entity.getIsAdmin()), SysUserDO::getIsAdmin, entity.getIsAdmin())
                .eq(!ObjectUtils.isEmpty(entity.getRegisteredTime()), SysUserDO::getRegisteredTime, entity.getRegisteredTime())
                .eq(!ObjectUtils.isEmpty(entity.getLastLoginTime()), SysUserDO::getLastLoginTime, entity.getLastLoginTime())
                ;
    }

    /**
     * 获取 {@link LambdaUpdateWrapper}
     *
     * @param entity 实体类
     * @return {@link LambdaUpdateWrapper}
     */
    @Override
    public LambdaUpdateWrapper<SysUserDO> lambdaUpdate(SysUserDO entity) {
        if (Objects.isNull(entity)) {
            return lambdaUpdate();
        }
        LambdaUpdateWrapper<SysUserDO> wrapper = new LambdaUpdateWrapper<>();
        return wrapper
                .set(SysUserDO::getNickName, entity.getNickName())
                .set(SysUserDO::getUserName, entity.getUserName())
                .set(SysUserDO::getPassWord, entity.getPassWord())
                .set(SysUserDO::getPassWordSalt, entity.getPassWordSalt())
                .set(SysUserDO::getPassWordOld, entity.getPassWordOld())
                .set(SysUserDO::getPassWordSaltOld, entity.getPassWordSaltOld())
                .set(SysUserDO::getDeptId, entity.getDeptId())
                .set(SysUserDO::getUserAvatar, entity.getUserAvatar())
                .set(SysUserDO::getUserType, entity.getUserType())
                .set(SysUserDO::getQqOpenid, entity.getQqOpenid())
                .set(SysUserDO::getWxOpenid, entity.getWxOpenid())
                .set(SysUserDO::getWxUnionid, entity.getWxUnionid())
                .set(SysUserDO::getIsLock, entity.getIsLock())
                .set(SysUserDO::getIsActive, entity.getIsActive())
                .set(SysUserDO::getIsAdmin, entity.getIsAdmin())
                .set(SysUserDO::getRegisteredTime, entity.getRegisteredTime())
                .set(SysUserDO::getLastLoginTime, entity.getLastLoginTime())
                ;
    }


}
