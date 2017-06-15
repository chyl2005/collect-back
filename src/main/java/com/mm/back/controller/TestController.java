package com.mm.back.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:chyl2005
 * Date:17/3/26
 * Time:10:13
 * Desc:描述该类的作用
 */

@Controller
@RequestMapping("/test")
public class TestController {




    @RequestMapping("/charSet")
    @ResponseBody
    public boolean charSet(String decode, String encode) {
        if (StringUtils.isNotBlank(decode)) {
           // ServerInitializer.decoder = decode;
        }
        if (StringUtils.isNotBlank(encode)) {
          //  ServerInitializer.encoder = encode;
        }
        return true;
    }

}
