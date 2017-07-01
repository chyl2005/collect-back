package com.mm.back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceConfigDao;
import com.mm.back.entity.DeviceConfigEntity;
import com.mm.back.dto.DeviceSettingDto;
import com.mm.back.vo.DeviceConfigVo;
import com.mm.back.service.DeviceConfigService;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:19
 * Desc:描述该类的作用
 */
@Service
public class DeviceConfigServiceImpl implements DeviceConfigService {

    @Autowired
    private DeviceConfigDao deviceConfigDao;

    @Transactional
    @Override
    public void insertOrUpdate(DeviceConfigEntity deviceConfig) {
        deviceConfigDao.insertOrUpdate(deviceConfig);
    }

    @Transactional
    @Override
    public void insertOrUpdate(DeviceSettingDto deviceSettingDto) {
        DeviceConfigEntity entity = parseToDeviceConfigEntity(deviceSettingDto);
        insertOrUpdate(entity);
    }

    @Override
    public DeviceConfigVo getConfigInfo(Integer deviceId) {
        DeviceConfigEntity deviceConfig = deviceConfigDao.getDeviceConfig(deviceId);
        DeviceConfigVo configResponse = ConvertUtils.parseToDeviceConfigVo(deviceConfig);
        return configResponse;
    }




    private DeviceConfigEntity parseToDeviceConfigEntity(DeviceSettingDto deviceSettingDto){
        DeviceConfigEntity entity = new DeviceConfigEntity();
        entity.setDeviceId(deviceSettingDto.getDeviceId());
        entity.setWakeupTime1(deviceSettingDto.getWakeupTime1());
        entity.setWakeupTime2(deviceSettingDto.getWakeupTime2());
        entity.setServerIp(deviceSettingDto.getIPAddress());
        entity.setServerPort(deviceSettingDto.getPortNumber());
        entity.setSurfaceHigh(deviceSettingDto.getSurfaceHigh());
        entity.setSensorDepth(deviceSettingDto.getSensorDepth());
        entity.setPhoneNum1(deviceSettingDto.getPhonenumber1());
        entity.setPhoneNum2(deviceSettingDto.getPhonenumber2());
        entity.setLinearCoefficient(deviceSettingDto.getLinearCoefficient());
        entity.setSoftwareVersion(deviceSettingDto.getSoftwareVersion());
        return entity;

    }
}
