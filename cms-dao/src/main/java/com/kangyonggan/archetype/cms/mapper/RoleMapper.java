package com.kangyonggan.archetype.cms.mapper;

import com.kangyonggan.archetype.cms.model.vo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 根据用户名查找角色
     *
     * @param username
     * @return
     */
    List<Role> selectRolesByUsername(String username);

    /**
     * 删除所有用户角色
     *
     * @param username
     */
    void deleteAllRolesByUsername(String username);
}