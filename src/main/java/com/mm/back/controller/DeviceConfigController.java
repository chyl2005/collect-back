package com.mm.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.entity.DeviceConfigEntity;
import com.mm.back.model.DeviceConfigResponse;
import com.mm.back.service.DeviceConfigService;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:14:30
 * Desc:描述该类的作用
 */
@Controller
@RequestMapping("/deviceConfig")
public class DeviceConfigController {

    @Autowired
    private DeviceConfigService deviceConfigService;

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public WebResponse saveOrUpdate(DeviceConfigEntity deviceConfig) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        deviceConfigService.insertOrUpdate(deviceConfig);
        return webResponse;
    }

    @RequestMapping("/info")
    @ResponseBody
    public WebResponse getConfigInfo(@RequestParam Integer deviceId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        DeviceConfigResponse configInfo = deviceConfigService.getConfigInfo(deviceId);
        webResponse.setData(configInfo);
        return webResponse;

    }

}
