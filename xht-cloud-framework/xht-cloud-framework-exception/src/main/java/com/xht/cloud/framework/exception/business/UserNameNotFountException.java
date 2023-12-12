package com.xht.cloud.framework.exception.business;

/**
 * 描述 ：用户账号不存在
 *
 * @author : 小糊涂
 **/
public class UserNameNotFountException extends BizException {

    /**
     * @param userName 用户名称
     */
    public UserNameNotFountException(String userName) {
        super(String.format("账号：`%s`不存在", userName));
    }

    /**
     *
     */
    public UserNameNotFountException() {
        super("账号不存在");
    }

}
