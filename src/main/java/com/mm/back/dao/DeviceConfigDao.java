package com.mm.back.dao;

import com.mm.back.entity.DeviceConfigEntity;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/15
 * Time:11:16
 * Desc:设备配置信息
 */
public interface DeviceConfigDao {

    /**
     * @param deviceConfig
     */
    void insertOrUpdate(DeviceConfigEntity deviceConfig);


    DeviceConfigEntity getDeviceConfig(Integer deviceId);

}
