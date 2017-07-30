package com.mm.back.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.dto.DeviceRecordDto;
import com.mm.back.dto.DeviceSettingData;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.netty.ServerHandler;
import com.mm.back.service.DeviceRecordService;
import com.mm.back.service.HandlerMessageService;
import com.mm.common.utils.DateUtils;
import com.mm.common.utils.JsonUtils;

/**
 * Author:chyl2005
 * Date:17/3/26
 * Time:10:13
 * Desc:描述该类的作用
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Autowired
    private DeviceRecordService deviceRecordService;

    @Autowired
    private HandlerMessageService messageService;

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

    @RequestMapping("/data")
    @ResponseBody
    public boolean data(@RequestBody DeviceSettingData data) {
        String s = JsonUtils.object2Json(data);
        return true;
    }

    @RequestMapping("/sendCommond")
    @ResponseBody
    public WebResponse sendCommond(String address, Integer command, String message) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        messageService.sendCommond(address, command, message);
        return webResponse;
    }

    @RequestMapping("/online")
    @ResponseBody
    public Map online() {
        return ServerHandler.addressToDeviceNumMap;
    }

    @RequestMapping("/getDeviceByDeviceNum")
    @ResponseBody
    public DeviceInfoEntity getDeviceByDeviceNum() {
        return this.deviceInfoDao.getDeviceByDeviceNum("YC-AB-0002");
    }

    @RequestMapping("/insertRecord")
    @ResponseBody
    public WebResponse insertRecord(Integer datekey) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        Date date = DateUtils.getDateByDatekey(datekey);
        for (int i = 1; i < 48; i++) {
            Date dateAfterMinutes = DateUtils.getDateAfterMinutes(date, 30 * i);
            insertTestReocrd(dateAfterMinutes);
        }
        return webResponse;
    }

    @RequestMapping("/del")
    @ResponseBody
    public WebResponse del(Integer datekey) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        Date date = DateUtils.getDateByDatekey(datekey);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 30);
        Date startTime = calendar.getTime();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar1.set(Calendar.HOUR_OF_DAY, 8);
        calendar1.set(Calendar.MINUTE, 29);
        calendar1.set(Calendar.SECOND, 30);
        Date endTime = calendar1.getTime();
        deviceRecordService.del(startTime, endTime);
        return webResponse;
    }

    private void insertTestReocrd(Date collectDate) {
        DeviceRecordDto record = new DeviceRecordDto();
        record.setDeviceNum("YC-AB-0002");
        record.setCollectTime(DateUtils.getDateformat(collectDate, DateUtils.YMD_HMS_FORMAT_DIAS));
        record.setSensorDepth(BigDecimal.valueOf(RandomUtils.nextInt(20, 50)));
        record.setSurfaceHigh(BigDecimal.valueOf(RandomUtils.nextInt(100, 500)));

        record.setWaterDepth(BigDecimal.valueOf(RandomUtils.nextInt(20, 200)));
        record.setWaterHigh(BigDecimal.valueOf(RandomUtils.nextInt(20, 200)));

        record.setAirTemperature(BigDecimal.valueOf(RandomUtils.nextInt(20, 30)));
        record.setWatertemperature(BigDecimal.valueOf(RandomUtils.nextInt(1, 30)));

        record.setVoltage(BigDecimal.valueOf(RandomUtils.nextInt(1, 10)));
        record.setSignal(BigDecimal.valueOf(RandomUtils.nextInt(20, 100)));
        deviceRecordService.insertOrUpdate(record);
    }
}
