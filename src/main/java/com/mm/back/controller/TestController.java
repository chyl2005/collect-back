package com.mm.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mm.back.service.CollectServer;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/3/26
 * Time:10:13
 * Desc:描述该类的作用
 */


@Controller
@RequestMapping("/test")
public class TestController {


    @Autowired
    private CollectServer collectServer;
    @RequestMapping("/service")
    public void service(){
        collectServer.service();
    }
}
