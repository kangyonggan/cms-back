package com.kangyonggan.archetype.cms.biz;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

/**
 * @author kangyonggan
 * @since 2017/1/23
 */
@Log4j2
public class Log4j2SMTPAppenderTest extends AbstractServiceTest {

    @Test
    public void test() {
        log.debug("this is debug");
        log.error("this is error");
        log.info("this is info");
        log.warn("this is warn");
    }

}
