package com.mm.back.dao;

import com.mm.back.entity.DeviceSettingEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:11:16
 * Desc:设备配置信息
 */
public interface DeviceSettingDao {


    void insert(DeviceSettingEntity setting);
    /**
     * @param deviceConfig
     */
    void insertOrUpdate(DeviceSettingEntity deviceConfig);


    DeviceSettingEntity getSetting(Integer deviceId);

}
