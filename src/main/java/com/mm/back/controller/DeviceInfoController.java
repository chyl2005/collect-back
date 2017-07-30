package com.mm.back.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.AoData;
import com.mm.back.common.WebResponse;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.entity.DeviceSettingEntity;
import com.mm.back.service.DeviceSettingService;
import com.mm.back.service.DeviceInfoService;
import com.mm.back.service.DeviceUploadSettingService;
import com.mm.back.vo.DeviceInfoVo;
import com.mm.back.vo.DeviceSettingVo;
import com.mm.common.utils.UserUtils;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:14:30
 * Desc:描述该类的作用
 */
@Controller
@RequestMapping("/deviceInfo")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;


    /**
     * 设备列表
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "device/index";
    }


    /**
     * 设备配置信息
     *
     * @return
     */
    @RequestMapping("/config")
    public String config() {
        return "device/config";
    }

    @RequestMapping("/list")
    @ResponseBody
    public WebResponse list() {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        AoData<List<DeviceInfoVo>> deviceInfos = deviceInfoService.getDeviceInfos(UserUtils.getUser().getId());
        webResponse.setData(deviceInfos);
        return webResponse;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public WebResponse saveOrUpdate(DeviceInfoEntity deviceInfo) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        this.deviceInfoService.insertOrUpdate(deviceInfo);
        return webResponse;
    }


    @RequestMapping("/del")
    @ResponseBody
    public WebResponse del(@RequestParam Integer deviceId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        this.deviceInfoService.updateDelStatus(deviceId, DeleteStatusEnum.DEL.getCode());
        return webResponse;
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public WebResponse updateStatus(@RequestParam Integer deviceId, @RequestParam Integer isDel) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        this.deviceInfoService.updateDelStatus(deviceId, isDel);
        return webResponse;
    }






}
