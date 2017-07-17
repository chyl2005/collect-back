package com.mm.back.service;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mm.back.dto.DeviceRecordDto;
import com.mm.common.utils.DateUtils;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/9
 * Time:11:48
 * Desc:描述该类的作用
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"classpath:applicationcontext.xml"})
public class InsertDataTest {

    @Autowired
    private DeviceRecordService deviceRecordService;

    @Test
    public void insertRecord() {

        DeviceRecordDto record = new DeviceRecordDto();
        record.setDeviceNum("YC-AB-0002");
        Date collectDate = new Date();
        record.setCollectTime(DateUtils.getDateformat(collectDate, DateUtils.YMD_HMS_FORMAT_DIAS));

        record.setSensorDepth(BigDecimal.valueOf(RandomUtils.nextInt(20, 50)));
        record.setSurfaceHigh(BigDecimal.valueOf(RandomUtils.nextInt(200, 500)));

        record.setWaterDepth(BigDecimal.valueOf(RandomUtils.nextInt(20, 200)));
        record.setWaterHigh(BigDecimal.valueOf(RandomUtils.nextInt(200, 200)));

        record.setAirTemperature(BigDecimal.valueOf(RandomUtils.nextInt(20, 30)));
        record.setWatertemperature(BigDecimal.valueOf(RandomUtils.nextInt(1, 30)));

        record.setVoltage(BigDecimal.valueOf(RandomUtils.nextInt(1, 10)));
        record.setSignal(BigDecimal.valueOf(RandomUtils.nextInt(20, 100)));
        deviceRecordService.insertOrUpdate(record);
    }
}
