package com.mm.back.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.service.DeviceRecordService;
import com.mm.back.vo.DeviceRecordVo;
import com.mm.common.utils.DateUtils;
import com.mm.common.utils.DownloadUtil;

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

    /**
     * 下载
     *
     * @param deviceId
     */
    @RequestMapping("/download")
    public void download(@RequestParam Integer deviceId, @RequestParam Integer startTime,
                         @RequestParam Integer endTime, HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = deviceRecordService.download(deviceId, startTime, endTime);
        String filename="设备记录" + DateUtils.getDateformat(new Date(),DateUtils.yyyyMMddHHmmss) + ".xlsx";
        DownloadUtil.downloadExcel(request,response,filename,workbook);
    }
}
