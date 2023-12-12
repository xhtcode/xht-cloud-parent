package com.xht.cloud.system.moudle;

import com.xht.cloud.system.SystemApplication;
import com.xht.cloud.system.module.user.dao.dataobject.SysUserDO;
import com.xht.cloud.system.module.user.dao.mapper.SysUserMapper;
import com.xht.cloud.system.module.user.dao.mapper.SysUserProfileMapper;
import com.xht.cloud.system.module.user.dao.wrapper.SysUserWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@SpringBootTest(classes = SystemApplication.class)
public class SpringBootTest1 {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserProfileMapper sysUserProfileMapper;

    @Test
    public void test() {
        List<SysUserDO> sysUserDOS = sysUserMapper.selectList(SysUserWrapper.getInstance().lambdaQuery());
        for (SysUserDO sysUserDO : sysUserDOS) {
            String userName = sysUserDO.getUserName();
            if (userName.equals("admin")) {
                continue;
            }
            if (userName.length() == 1) {
                userName = "WZ000" + userName;
            }
            if (userName.length() == 2) {
                userName = "WZ00" + userName;
            }
            if (userName.length() == 3) {
                userName = "WZ0" + userName;
            }
            sysUserDO.setUserName(userName);
            sysUserMapper.updateById(sysUserDO);
        }
    }

}
