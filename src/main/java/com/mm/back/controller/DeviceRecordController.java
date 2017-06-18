package com.mm.back.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.model.DeviceRecordResponse;
import com.mm.back.service.DeviceRecordService;

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
    public String index() {
        return "device/record";
    }


    @RequestMapping("/list")
    @ResponseBody
    public WebResponse list( @RequestParam Integer deviceId,
                            @RequestParam Integer startTime, @RequestParam Integer endTime) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        List<DeviceRecordResponse> records = deviceRecordService.getRecords(deviceId, startTime, endTime);
        webResponse.setData(records);
        return webResponse;
    }
}
