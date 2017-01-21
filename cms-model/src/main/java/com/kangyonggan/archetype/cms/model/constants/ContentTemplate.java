package com.kangyonggan.archetype.cms.model.constants;

import lombok.Getter;

/**
 * 内容模板
 *
 * @author kangyonggan
 * @since 2016/12/3
 */
public enum ContentTemplate {

    PAGE("page", "页面");

    /**
     * 模板
     */
    @Getter
    private final String template;

    /**
     * 模板名称
     */
    @Getter
    private final String name;

    ContentTemplate(String template, String name) {
        this.template = template;
        this.name = name;
    }

}
