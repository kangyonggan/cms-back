package com.kangyonggan.archetype.cms.biz.service.impl;

import com.kangyonggan.archetype.cms.biz.service.UserService;
import com.kangyonggan.archetype.cms.biz.util.Digests;
import com.kangyonggan.archetype.cms.biz.util.Encodes;
import com.kangyonggan.archetype.cms.mapper.UserMapper;
import com.kangyonggan.archetype.cms.mapper.UserProfileMapper;
import com.kangyonggan.archetype.cms.model.annotation.CacheDelete;
import com.kangyonggan.archetype.cms.model.annotation.CacheDeleteAll;
import com.kangyonggan.archetype.cms.model.annotation.CacheGetOrSave;
import com.kangyonggan.archetype.cms.model.annotation.LogTime;
import com.kangyonggan.archetype.cms.model.constants.AppConstants;
import com.kangyonggan.archetype.cms.model.vo.ShiroUser;
import com.kangyonggan.archetype.cms.model.vo.User;
import com.kangyonggan.archetype.cms.model.vo.UserProfile;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    @LogTime
    public User findUser4Login(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        // 用户名
        if (username.matches("^[a-z]\\w+")) {
            return findUserByUsername(username);
        }

        // 邮箱
        if (username.contains("@")) {
            return findUserByEmail(username);
        }

        // 手机号
        if (username.matches("^1\\d{10}$")) {
            return findUserByMobile(username);
        }

        return null;
    }

    @Override
    @LogTime
    public User findUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);

        return super.selectOne(user);
    }

    @Override
    @LogTime
    public User findUserByMobile(String mobile) {
        User user = new User();
        user.setMobile(mobile);

        return super.selectOne(user);
    }

    @Override
    @LogTime
    public User findUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);

        return super.selectOne(user);
    }

    @Override
    @LogTime
    public ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    @LogTime
    @CacheGetOrSave("user:id:{0}")
    public User findUserById(Long id) {
        User user =  super.selectByPrimaryKey(id);

        if (user != null) {
            user.setPassword(null);
            user.setSalt(null);
        }
        return user;
    }

    @Override
    @LogTime
    public boolean existsUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectCount(user) == 1;
    }

    @Override
    @LogTime
    @CacheDeleteAll("user:all")
    public void saveUserWithDefaultRole(User user) {
        entryptPassword(user);
        super.insertSelective(user);

        saveUserProfile(user);

        saveUserRoles(user.getUsername(), AppConstants.DEFAULT_ROLE_CODE);
    }

    @Override
    @LogTime
    public boolean existsEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return userMapper.selectCount(user) == 1;
    }

    @Override
    @LogTime
    @CacheDelete("user:id:{0:id}")
    public void updateUserPassword(User user) {
        entryptPassword(user);
        super.updateByPrimaryKeySelective(user);
    }

    /**
     * 保存用户基础信息
     *
     * @param user
     */
    private void saveUserProfile(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(user.getUsername());

        userProfileMapper.insertSelective(userProfile);
    }

    /**
     * 批量保存用户角色
     *
     * @param username
     * @param roleCodes
     */
    private void saveUserRoles(String username, String roleCodes) {
        userMapper.insertUserRoles(username, Arrays.asList(roleCodes.split(",")));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过N次 sha-1 hash
     *
     * @param user
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(AppConstants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, AppConstants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
