package com.kangyonggan.archetype.cms.biz.service.impl;

import com.kangyonggan.archetype.cms.biz.service.MailService;
import com.kangyonggan.archetype.cms.model.annotation.LogTime;
import com.kangyonggan.archetype.cms.model.vo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 2017/1/19
 */
@Service
@Log4j2
public class MailServiceImpl implements MailService {

    @Override
    @LogTime
    public void sendResetMail(User user, String callbackUrl) {
        log.info("邮件发送成功");
    }
}
