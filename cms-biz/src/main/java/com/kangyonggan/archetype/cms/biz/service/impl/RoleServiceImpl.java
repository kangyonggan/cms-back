package com.kangyonggan.archetype.cms.biz.service.impl;

import com.kangyonggan.archetype.cms.biz.service.RoleService;
import com.kangyonggan.archetype.cms.mapper.RoleMapper;
import com.kangyonggan.archetype.cms.model.annotation.CacheGetOrSave;
import com.kangyonggan.archetype.cms.model.annotation.LogTime;
import com.kangyonggan.archetype.cms.model.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @LogTime
    @CacheGetOrSave("role:username:{0}")
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }

    @Override
    @LogTime
    public boolean existsRoleCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return roleMapper.selectCount(role) == 1;
    }
}
