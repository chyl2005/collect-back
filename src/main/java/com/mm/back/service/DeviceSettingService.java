package com.mm.back.service;

import com.mm.back.dto.DeviceSettingDto;
import com.mm.back.entity.DeviceSettingEntity;
import com.mm.back.vo.DeviceSettingVo;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:07
 * Desc:描述该类的作用
 */
public interface DeviceSettingService {

    void saveIfNotExist(DeviceSettingDto deviceSettingDto);

    void insertOrUpdate(DeviceSettingEntity deviceConfig);
    void insertOrUpdate(DeviceSettingDto deviceSettingDto);

    DeviceSettingVo getSetting(Integer deviceId);


    DeviceSettingDto getSettingDto(Integer deviceId);


    DeviceSettingDto getSettingDto(String deviceNum);
}
