package com.mm.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/17
 * Time:21:43
 * Desc:描述该类的作用
 */
@Controller
@RequestMapping("/my")
public class MyController {



    @RequestMapping("/info")
    public String index(Integer deviceId, Model model) {
        model.addAttribute("deviceId", deviceId);
        return "my/index";
    }
}
