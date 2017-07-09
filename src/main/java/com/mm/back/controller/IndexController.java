package com.mm.back.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.Menu;
import com.mm.back.common.User;
import com.mm.back.common.WebResponse;
import com.mm.back.utils.UserUtils;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/17
 * Time:21:43
 * Desc:描述该类的作用
 */
@Controller
@RequestMapping("/index")
public class IndexController {


    @RequestMapping("/index")
    public String index(Integer deviceId, Model model) {
        model.addAttribute("deviceId", deviceId);
        return "index/index";
    }

    /**
     * @return List<Module> 返回类型
     * @Description: 根据用户信息查询左边栏
     * @author chenyanlong
     * @date 2015年11月23日 下午2:26:35
     */
    @RequestMapping("/left")
    @ResponseBody
    public WebResponse getLeftModuleList() {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        // 检查页面的cookie
        List<Menu> menus = UserUtils.getMenus();
        webResponse.setData(menus);
        return webResponse;
    }


    @RequestMapping("/logininfo")
    @ResponseBody
    public WebResponse logininfo() {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        // 检查页面的cookie
        User user = UserUtils.getUser();
        webResponse.setData(user);
        return webResponse;
    }
}
