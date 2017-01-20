package com.kangyonggan.archetype.cms.biz.service;

import com.kangyonggan.archetype.cms.model.vo.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
public interface MenuService {

    /**
     * 根据用户名查找菜单
     *
     * @param username
     * @return
     */
    List<Menu> findMenusByUsername(String username);

    /**
     * 校验菜单代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsMenuCode(String code);

    /**
     * 查找角色菜单
     *
     * @param code
     * @return
     */
    List<Menu> findMenus4Role(String code);

    /**
     * 查找所有菜单
     *
     * @return
     */
    List<Menu> findAllMenus();
}
