package com.mm.back.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.dto.DeviceRecordDto;
import com.mm.back.service.DeviceRecordService;
import com.mm.back.vo.DeviceRecordVo;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:14:30
 * Desc:描述该类的作用
 */
@Controller
@RequestMapping("/deviceRecord")
public class DeviceRecordController {

    @Autowired
    private DeviceRecordService deviceRecordService;

    @RequestMapping("/index")
    public String index(Integer deviceId, Model model) {
        model.addAttribute("deviceId", deviceId);
        return "device/record";
    }

    @RequestMapping("/list")
    @ResponseBody
    public WebResponse list(@RequestParam Integer deviceId,
                            @RequestParam Integer startTime, @RequestParam Integer endTime) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        List<DeviceRecordVo> records = deviceRecordService.getRecords(deviceId, startTime, endTime);
        webResponse.setData(records);
        return webResponse;
    }
}
