package com.mm.back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceUploadSettingDao;
import com.mm.back.dto.DeviceSettingDto;
import com.mm.back.entity.DeviceUploadSettingEntity;
import com.mm.back.service.DeviceUploadSettingService;
import com.mm.back.vo.DeviceSettingVo;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:19
 * Desc:描述该类的作用
 */
@Service
public class DeviceUploadSettingServiceImpl implements DeviceUploadSettingService {

    @Autowired
    private DeviceUploadSettingDao uploadSettingDao;


    @Transactional
    @Override
    public void insertOrUpdate(DeviceSettingDto deviceSettingDto) {
        DeviceUploadSettingEntity entity = parseToDeviceConfigEntity(deviceSettingDto);
        uploadSettingDao.insertOrUpdate(entity);
    }

    @Override
    public DeviceSettingVo getSetting(Integer deviceId) {
        DeviceUploadSettingEntity deviceConfig = uploadSettingDao.getSetting(deviceId);
        DeviceSettingVo configResponse = ConvertUtils.parseToDeviceConfigVo(deviceConfig);
        return configResponse;
    }

    private DeviceUploadSettingEntity parseToDeviceConfigEntity(DeviceSettingDto deviceSettingDto) {
        DeviceUploadSettingEntity entity = new DeviceUploadSettingEntity();
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
