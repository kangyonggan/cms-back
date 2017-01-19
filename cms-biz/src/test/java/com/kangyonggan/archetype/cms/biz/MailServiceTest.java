package com.kangyonggan.archetype.cms.biz;

import com.kangyonggan.archetype.cms.biz.service.MailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
public class MailServiceTest extends AbstractServiceTest {

    @Autowired
    private MailService mailService;

    /**
     * 测试发送找回密码的邮件
     */
    @Test
    public void testSendResetMail() {
        mailService.send("2825176081@qq.com", "test:<h1>测试邮件</h1>", true);
    }

}
