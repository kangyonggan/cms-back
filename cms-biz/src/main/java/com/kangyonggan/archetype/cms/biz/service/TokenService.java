package com.kangyonggan.archetype.cms.biz.service;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
public interface TokenService {

    /**
     * 保存记号
     *
     * @param type
     * @param userId
     * @return
     */
    String saveToken(String type, Long userId);
}
