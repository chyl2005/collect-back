package com.mm.back.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/14
 * Time:17:56
 * Desc:描述该类的作用
 */
@Component
public class Config implements InitializingBean{
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    @Value("${loginkey}")
    private String loginkey ;
    @Value("${authCode}")
    private String authcode;
    @Value("${loginUrl}")
    private String loginUrl;

    @Value("${successUrl}")
    private  String successUrl ;


    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Config   loginkey={} authcode={} successUrl={}",loginkey,authcode,successUrl);
    }

    public String getLoginkey() {
        return loginkey;
    }

    public String getAuthcode() {
        return authcode;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }
}
