package com.mm.back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceSettingDao;
import com.mm.back.entity.DeviceSettingEntity;
import com.mm.back.dto.DeviceSettingDto;
import com.mm.back.entity.DeviceUploadSettingEntity;
import com.mm.back.vo.DeviceSettingVo;
import com.mm.back.service.DeviceSettingService;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:19
 * Desc:描述该类的作用
 */
@Service
public class DeviceSettingServiceImpl implements DeviceSettingService {

    @Autowired
    private DeviceSettingDao deviceSettingDao;

    @Override
    public void saveIfNotExist(DeviceSettingDto deviceSettingDto) {
        DeviceSettingEntity deviceConfig = deviceSettingDao.getSetting(deviceSettingDto.getDeviceId());
        if (deviceConfig == null) {
            deviceSettingDao.insert(parseToDeviceConfigEntity(deviceSettingDto));
        }
    }

    @Transactional
    @Override
    public void insertOrUpdate(DeviceSettingEntity deviceConfig) {
        deviceSettingDao.insertOrUpdate(deviceConfig);
    }

    @Transactional
    @Override
    public void insertOrUpdate(DeviceSettingDto deviceSettingDto) {
        DeviceSettingEntity entity = parseToDeviceConfigEntity(deviceSettingDto);
        insertOrUpdate(entity);
    }

    @Override
    public DeviceSettingVo getSetting(Integer deviceId) {
        DeviceSettingEntity deviceConfig = deviceSettingDao.getSetting(deviceId);
        DeviceSettingVo configResponse = ConvertUtils.parseToDeviceConfigVo(deviceConfig);
        return configResponse;
    }




    private DeviceSettingEntity parseToDeviceConfigEntity(DeviceSettingDto deviceSettingDto){
        DeviceSettingEntity entity = new DeviceSettingEntity();
        entity.setDeviceId(deviceSettingDto.getDeviceId());
        entity.setDeviceNum(deviceSettingDto.getDeviceNum());
        entity.setSerialNum(deviceSettingDto.getWellNum());
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
