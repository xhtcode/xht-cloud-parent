package com.xht.cloud.system.module.user.service.impl;

import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xht.cloud.framework.core.api.response.PageResponse;
import com.xht.cloud.framework.core.constant.CommonConstants;
import com.xht.cloud.framework.core.support.StringUtils;
import com.xht.cloud.framework.exception.Assert;
import com.xht.cloud.framework.exception.business.UserNameNotFountException;
import com.xht.cloud.framework.mybatis.core.DataScopeFieldBuilder;
import com.xht.cloud.framework.mybatis.core.enums.DataScopeTypeEnums;
import com.xht.cloud.framework.mybatis.handler.DataScopeFactory;
import com.xht.cloud.framework.mybatis.tool.PageTool;
import com.xht.cloud.framework.redis.key.RedisKeyTool;
import com.xht.cloud.framework.redis.service.RedisService;
import com.xht.cloud.framework.security.constant.UserTypeEnums;
import com.xht.cloud.framework.security.support.SecurityContextUtil;
import com.xht.cloud.system.enums.MenuTypeEnums;
import com.xht.cloud.system.manager.MinioManager;
import com.xht.cloud.system.module.dept.controller.response.SysDeptResponse;
import com.xht.cloud.system.module.dept.convert.SysDeptConvert;
import com.xht.cloud.system.module.dept.dao.dataobject.SysDeptDO;
import com.xht.cloud.system.module.dept.dao.mapper.SysDeptMapper;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysMenuDO;
import com.xht.cloud.system.module.permissions.dao.dataobject.SysRoleDO;
import com.xht.cloud.system.module.permissions.dao.mapper.SysMenuMapper;
import com.xht.cloud.system.module.permissions.dao.mapper.SysRoleMapper;
import com.xht.cloud.system.module.user.controller.request.SysUserBaseAddUpdate;
import com.xht.cloud.system.module.user.controller.request.SysUserProfileRequest;
import com.xht.cloud.system.module.user.controller.request.SysUserQueryRequest;
import com.xht.cloud.system.module.user.controller.request.UpdatePassWordRequest;
import com.xht.cloud.system.module.user.controller.response.SysUserProfileResponse;
import com.xht.cloud.system.module.user.controller.response.SysUserResponse;
import com.xht.cloud.system.module.user.controller.response.SysUserVo;
import com.xht.cloud.system.module.user.convert.SysUserConvert;
import com.xht.cloud.system.module.user.convert.SysUserProfileConvert;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserDO;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserProfileDO;
import com.xht.cloud.system.module.user.dao.mapper.SysUserMapper;
import com.xht.cloud.system.module.user.dao.mapper.SysUserProfileMapper;
import com.xht.cloud.system.module.user.dao.wrapper.SysUserWrapper;
import com.xht.cloud.system.module.user.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 描述 ：用户
 *
 * @author : xht
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    private final SysUserProfileMapper sysUserProfileMapper;

    private final SysUserConvert sysUserConvert;

    private final SysUserProfileConvert sysUserProfileConvert;

    private final SysRoleMapper sysRoleMapper;

    private final SysMenuMapper sysMenuMapper;

    private final DataScopeFactory dataScopeFactory;

    private final PasswordEncoder passwordEncoder;

    private final RedisService redisService;

    private final SysDeptConvert sysDeptConvert;

    private final SysDeptMapper sysDeptMapper;

    private final MinioManager minioManager;


    /**
     * 创建
     *
     * @param request {@link SysUserBaseAddUpdate}
     * @return {@link String} 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(SysUserBaseAddUpdate request) {
        SysUserDO entity = sysUserConvert.toDO(request.getSysUser());
        entity.setUserName("CS" + RandomUtil.randomNumbers(5));
        String randomGenerator = new RandomGenerator(6).generate();
        entity.setPassWord(passwordEncoder.encode(String.format("123456%s", randomGenerator)));
        entity.setPassWordSalt(randomGenerator);
        entity.setIsActive("1");
        entity.setIsLock("1");
        entity.setIsAdmin("0");
        entity.setUserType("1");
        entity.setRegisteredTime(LocalDateTime.now());
        SysUserProfileRequest profile = request.getProfile();
        if (Objects.isNull(profile)) {
            profile = new SysUserProfileRequest();
        }
        SysUserProfileDO sysUserProfileDO = sysUserProfileConvert.toDO(profile);
        sysUserMapper.insert(entity);
        sysUserProfileDO.setUserId(entity.getId());
        sysUserProfileMapper.insert(sysUserProfileDO);
        return entity.getUserName();
    }

    /**
     * 根据id修改
     *
     * @param request {@link SysUserBaseAddUpdate}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(SysUserBaseAddUpdate request) {
        String userId = request.getSysUser().getId();
        SysUserDO sysUserDO = sysUserMapper.selectOne(SysUserDO::getId, userId).orElse(null);
        if (Objects.isNull(sysUserDO)) {
            Assert.fail("修改的对象不存在");
        }
        SysUserProfileDO sysUserProfileDO = sysUserProfileConvert.toDO(request.getProfile());
        sysUserProfileDO.setUserId(userId);
        SysUserProfileDO dbSysUserProfileDO = sysUserProfileMapper.selectOne(SysUserProfileDO::getUserId, userId).orElse(null);
        if (Objects.nonNull(dbSysUserProfileDO)) {
            sysUserProfileDO.setId(dbSysUserProfileDO.getId());
            sysUserProfileMapper.updateById(sysUserProfileDO);
        } else {
            sysUserProfileMapper.insert(sysUserProfileDO);
        }
        redisService.delete(RedisKeyTool.createNameTemplate("user:profile:info:{}", sysUserDO.getUserName()));
        sysUserMapper.updateById(sysUserConvert.toDO(request.getSysUser()));
        return sysUserDO.getUserName();
    }

    /**
     * 删除
     *
     * @param ids {@link List<String>} id集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        List<SysUserDO> sysUserDOS = sysUserMapper.selectBatchIds(ids);
        SysUserDO sysUserDO = sysUserDOS.stream().filter(item -> Objects.equals(UserTypeEnums.ADMIN.getValue(), item.getUserType())).findFirst().orElse(null);
        Assert.isFalse(Objects.nonNull(sysUserDO), "权限不足禁止删除！");
        Set<String> collect = sysUserDOS.stream().map(item -> RedisKeyTool.createNameTemplate("user:profile:info:{}", item.getUserName())).collect(Collectors.toSet());
        redisService.delete(collect);
        sysUserMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id查询详细
     *
     * @param id {@link String} 数据库主键
     * @return {@link SysUserResponse}
     */
    @Override
    public SysUserVo findById(String id) {
        SysUserVo result = new SysUserVo();
        SysUserProfileResponse sysUserProfileResponse = null;
        SysUserResponse sysUserResponse = sysUserConvert.toResponse(sysUserMapper.findById(id).orElse(null));
        if (Objects.nonNull(sysUserResponse)) {
            sysUserProfileResponse = sysUserProfileConvert.toResponse(sysUserProfileMapper.selectOne(SysUserProfileDO::getUserId, sysUserResponse.getId()).orElse(new SysUserProfileDO()));
        }
        result.setSysUser(sysUserResponse);
        result.setProfile(sysUserProfileResponse);
        return result;
    }

    /**
     * 分页查询
     *
     * @param queryRequest {@link SysUserQueryRequest}
     * @return {@link PageResponse<SysUserResponse>} 分页详情
     */
    @Override
    public PageResponse<SysUserResponse> findPage(SysUserQueryRequest queryRequest) {
        LambdaQueryWrapper<SysUserDO> lambdaQuery = SysUserWrapper.getInstance().lambdaQuery(sysUserConvert.toDO(queryRequest));
        dataScopeFactory.getDataScopeHandler(DataScopeTypeEnums.DEPT_USER_TYPE).execute(DataScopeFieldBuilder.<SysUserDO>builder()
                .deptField(SysUserDO::getDeptId)
                .userField(SysUserDO::getId)
                .build(), lambdaQuery);
        if (!CollectionUtils.isEmpty(queryRequest.getUserIds())) {
            lambdaQuery.in(SysUserDO::getId, queryRequest.getUserIds());
        }
        IPage<SysUserDO> sysUserIPage = sysUserMapper.selectPage(PageTool.getPage(queryRequest), lambdaQuery);
        return sysUserConvert.toPageResponse(sysUserIPage);
    }

    /**
     * 根据userName查询详细
     *
     * @param userName     {@link String} 用户名称
     * @return {@link SysUserVo}
     */
    @Override
    public SysUserVo findByUserName(String userName) {
        return redisService.getKey(RedisKeyTool.createNameTemplate("user:profile:info:{}", userName), RandomUtil.randomInt(30, 60), TimeUnit.MINUTES, () -> {
            SysUserVo sysUserVo = new SysUserVo();
            SysUserDO sysUserDO = sysUserMapper.selectOne(SysUserDO::getUserName, userName).orElse(null);
            Assert.notNull(sysUserDO, String.format("账号`%s`不存在", userName));
            assert sysUserDO != null;
            String userId = sysUserDO.getId();
            sysUserVo.setSysUser(sysUserConvert.toResponse(sysUserDO));
            sysUserVo.setProfile(sysUserProfileConvert.toResponse(sysUserProfileMapper.selectOne(SysUserProfileDO::getUserId, userId).orElse(null)));
            List<SysRoleDO> sysRoleDOS = sysRoleMapper.selectListByUserId(userId);
            if (!CollectionUtils.isEmpty(sysRoleDOS)) {
                sysUserVo.setRoleCode(sysRoleDOS.stream().map(SysRoleDO::getRoleCode).collect(Collectors.toSet()));
                sysUserVo.setDataScope(sysRoleDOS.stream().map(item -> item.getDataScope().getValue()).min(Comparator.comparingInt(o -> o)).orElse(null));
            }
            List<SysMenuDO> sysMenuDOS;
            if (SecurityContextUtil.isAdmin()) {
                sysMenuDOS = sysMenuMapper.selectListIn(SysMenuDO::getMenuType, Arrays.stream(MenuTypeEnums.values()).map(MenuTypeEnums::getValue).toList());
            } else {
                sysMenuDOS = sysMenuMapper.selectByUserIdAndMenuType(sysUserDO.getId(), Arrays.stream(MenuTypeEnums.values()).map(MenuTypeEnums::getValue).toList());
            }
            if (!CollectionUtils.isEmpty(sysMenuDOS)) {
                sysUserVo.setAuthorities(sysMenuDOS.stream().filter(Objects::nonNull).map(SysMenuDO::getMenuAuthority).filter(StringUtils::hasText).collect(Collectors.toSet()));
            }
            if (Objects.nonNull(sysUserDO.getDeptId())) {
                if (Objects.equals(CommonConstants.TREE_DEFAULT, sysUserDO.getDeptId())) {
                    SysDeptResponse deptResponse = getDeptResponse();
                    sysUserVo.setDept(deptResponse);
                } else {
                    SysDeptDO sysDeptDO = sysDeptMapper.selectById(sysUserDO.getDeptId());
                    sysUserVo.setDept(sysDeptConvert.toResponse(sysDeptDO));
                }
            }
            return sysUserVo;
        });
    }

    @NotNull
    private static SysDeptResponse getDeptResponse() {
        SysDeptResponse deptResponse = new SysDeptResponse();
        deptResponse.setId(CommonConstants.TREE_DEFAULT);
        deptResponse.setDeptName("顶级部门");
        deptResponse.setDeptSort(1);
        deptResponse.setDeptStatus("1");
        return deptResponse;
    }

    /**
     * 校验账号是否存在
     *
     * @param userName 用户名称
     * @return {@link Boolean}
     */
    @Override
    public boolean validationUserName(String userName) {
        SysUserDO sysUserDO = sysUserMapper.selectOne(SysUserDO::getUserName, userName).orElse(null);
        return Objects.nonNull(sysUserDO);
    }

    /**
     * 修改登录用户信息
     *
     * @param userName 账号
     * @param request  请求信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserProfile(String userName, SysUserProfileRequest request) {
        SysUserDO sysUserDO = sysUserMapper.selectOne(SysUserDO::getUserName, userName).orElse(null);
        if (Objects.isNull(sysUserDO)) {
            throw new UserNameNotFountException(userName);
        }
        SysUserProfileDO sysUserProfileDO = sysUserProfileMapper.selectOne(SysUserProfileDO::getUserId, sysUserDO.getId()).orElse(null);
        SysUserProfileDO sysUserProfileDORequest = sysUserProfileConvert.toDO(request);
        sysUserProfileDORequest.setUserId(sysUserDO.getId());
        if (Objects.isNull(sysUserProfileDO)) {
            sysUserProfileDORequest.setId(null);
            sysUserProfileMapper.insert(sysUserProfileDORequest);
        } else {
            sysUserProfileDORequest.setId(sysUserProfileDO.getId());
            sysUserProfileMapper.updateById(sysUserProfileDORequest);
        }
        sysUserDO.setNickName(sysUserProfileDORequest.getRealName());
        sysUserMapper.updateById(sysUserDO);
        redisService.delete(RedisKeyTool.createNameTemplate("user:profile:info:{}", userName));
    }

    /**
     * 修改当前登录用户密码
     *
     * @param userName 账号
     * @param request  请求信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(String userName, UpdatePassWordRequest request) {
        SysUserDO sysUserDO = sysUserMapper.selectOne(SysUserDO::getUserName, userName).orElse(null);
        if (Objects.isNull(sysUserDO)) {
            throw new UserNameNotFountException(userName);
        }
        if (!passwordEncoder.matches(request.getOldPassword() + sysUserDO.getPassWordSalt(), sysUserDO.getPassWord())) {
            throw new RuntimeException("输入旧密码不正确!");
        }
        if (passwordEncoder.matches(request.getNewPassword() + sysUserDO.getPassWordSalt(), sysUserDO.getPassWord())) {
            throw new RuntimeException("新密码不能与旧密码相同!");
        }
        if (passwordEncoder.matches(request.getNewPassword() + sysUserDO.getPassWordSaltOld(), sysUserDO.getPassWordOld())) {
            throw new RuntimeException("不能设置使用过的密码!");
        }
        String randomGenerator = new RandomGenerator(6).generate();
        LambdaUpdateWrapper<SysUserDO> eq = SysUserWrapper.getInstance().lambdaUpdate()
                .set(SysUserDO::getPassWord, passwordEncoder.encode(String.format("%s%s", request.getNewPassword(), randomGenerator)))
                .set(SysUserDO::getPassWordSalt, randomGenerator)
                .set(SysUserDO::getPassWordOld, sysUserDO.getPassWordOld())
                .set(SysUserDO::getPassWordSaltOld, sysUserDO.getPassWordSaltOld())
                .eq(SysUserDO::getId, sysUserDO.getId());
        sysUserMapper.update(eq);
        redisService.delete(RedisKeyTool.createNameTemplate("user:profile:info:{}", userName));
    }


    /**
     * 重置账号密码
     *
     * @param userId 用户id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String resetUserPassword(String userId) {
        SysUserDO sysUserDO = sysUserMapper.findById(userId).orElse(null);
        if (Objects.isNull(sysUserDO)) {
            throw new UserNameNotFountException();
        }
        String userName = sysUserDO.getUserName();
        String randomGenerator = new RandomGenerator(6).generate();
        sysUserMapper.update(SysUserWrapper.getInstance().lambdaUpdate()
                .set(SysUserDO::getPassWord, passwordEncoder.encode(String.format("%s%s", "123456", randomGenerator)))
                .set(SysUserDO::getPassWordSalt, randomGenerator)
                .set(SysUserDO::getPassWordOld, sysUserDO.getPassWordOld())
                .set(SysUserDO::getPassWordSaltOld, sysUserDO.getPassWordSaltOld())
                .eq(SysUserDO::getId, sysUserDO.getId()));
        redisService.delete(RedisKeyTool.createNameTemplate("user:profile:info:{}", userName));
        return userName;
    }

    /**
     * 修改当前登录用户头像
     *
     * @param userName    账号
     * @param inputStream 头像io流
     * @return 头像地址
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateUserAvatar(String userName, InputStream inputStream) {
        SysUserDO sysUserDO = sysUserMapper.selectOne(SysUserDO::getUserName, userName).orElse(null);
        if (Objects.isNull(sysUserDO)) {
            throw new UserNameNotFountException(userName);
        }
        String result = minioManager.uploadUserAvatar(userName, IoUtil.readBytes(inputStream));
        sysUserDO.setUserAvatar(result);
        sysUserMapper.updateById(sysUserDO);
        redisService.delete(RedisKeyTool.createNameTemplate("user:profile:info:{}", userName));
        return result;
    }
}

