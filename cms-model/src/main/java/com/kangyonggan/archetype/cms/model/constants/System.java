package com.kangyonggan.archetype.cms.model.constants;

import lombok.Getter;

/**
 * 系统
 *
 * @author kangyonggan
 * @since 2016/12/3
 */
public enum System {

    CMS("cms", "内容管理系统");

    /**
     * 系统代码
     */
    @Getter
    private final String code;

    /**
     * 系统名称
     */
    @Getter
    private final String name;

    System(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
