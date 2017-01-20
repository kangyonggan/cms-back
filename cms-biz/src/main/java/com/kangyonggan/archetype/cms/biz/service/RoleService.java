package com.kangyonggan.archetype.cms.biz.service;

import com.kangyonggan.archetype.cms.model.vo.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
public interface RoleService {

    /**
     * 根据用户名查找角色
     *
     * @param username
     * @return
     */
    List<Role> findRolesByUsername(String username);

    /**
     * 校验角色代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsRoleCode(String code);

    /**
     * 查找所有角色
     *
     * @return
     */
    List<Role> findAllRoles();

}
