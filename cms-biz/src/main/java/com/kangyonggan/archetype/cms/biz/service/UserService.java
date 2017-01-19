package com.kangyonggan.archetype.cms.biz.service;

import com.kangyonggan.archetype.cms.model.vo.ShiroUser;
import com.kangyonggan.archetype.cms.model.vo.User;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
public interface UserService {

    /**
     * 查找用户，用于登录，username可以是手机号和邮箱
     *
     * @param username
     * @return
     */
    User findUser4Login(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 根据手机号查找用户
     *
     * @param mobile
     * @return
     */
    User findUserByMobile(String mobile);

    /**
     * 根据邮箱查找用户
     *
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    ShiroUser getShiroUser();

    /**
     * 根据ID查找用户，不会查出密码
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 校验用户名是否存在
     *
     * @param username
     * @return
     */
    boolean existsUsername(String username);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUserWithDefaultRole(User user);

    /**
     * 校验邮件是否存在
     *
     * @param email
     * @return
     */
    boolean existsEmail(String email);
}
