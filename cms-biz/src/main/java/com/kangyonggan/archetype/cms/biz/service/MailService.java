package com.kangyonggan.archetype.cms.biz.service;

import com.kangyonggan.archetype.cms.model.vo.User;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
public interface MailService {

    /**
     * 发找回密码的邮件
     *
     * @param user
     * @param callbackUrl
     */
    void sendResetMail(User user, String callbackUrl);

    /**
     * 发送邮件
     *
     * @param to
     * @param text
     * @param isHtml
     */
    void send(String to, String text, boolean isHtml);
}
