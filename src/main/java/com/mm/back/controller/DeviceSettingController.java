package com.mm.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.entity.DeviceSettingEntity;
import com.mm.back.service.DeviceSettingService;
import com.mm.back.service.DeviceUploadSettingService;
import com.mm.back.vo.DeviceSettingVo;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/15
 * Time:12:31
 * Desc:描述该类的作用
 */
@Controller
@RequestMapping("/deviceSetting")
public class DeviceSettingController {


    @Autowired
    private DeviceSettingService deviceSettingService;


    @Autowired
    private DeviceUploadSettingService uploadSettingService;

    /**
     * 设备配置信息
     *
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Integer deviceId, Model model) {
        model.addAttribute("deviceId", deviceId);
        return "device/edit";
    }



    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public WebResponse saveOrUpdate(DeviceSettingEntity deviceSetting) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        if (deviceSetting.getDeviceId() == null) {
            throw new RuntimeException("设备ID不能为NULL");
        }
        deviceSettingService.insertOrUpdate(deviceSetting);
        return webResponse;
    }

    /**
     * 服务器机器配置
     *
     * @param deviceId
     * @return
     */
    @RequestMapping("/setting")
    @ResponseBody
    public WebResponse setting(Integer deviceId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        if (deviceId != null) {
            DeviceSettingVo deviceSetting = deviceSettingService.getSetting(deviceId);
            webResponse.setData(deviceSetting);
        }
        return webResponse;

    }

    /**
     * 客户端上传参数配置
     *
     * @param deviceId
     * @return
     */
    @RequestMapping("/uploadSetting")
    @ResponseBody
    public WebResponse uploadSetting( Integer deviceId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        if (deviceId != null) {
            DeviceSettingVo setting = uploadSettingService.getSetting(deviceId);
            webResponse.setData(setting);
        }
        return webResponse;

    }

}
