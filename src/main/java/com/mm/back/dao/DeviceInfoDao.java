package com.mm.back.dao;

import com.mm.back.entity.DeviceInfoEntity;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/15
 * Time:11:15
 * Desc:设备基础信息
 */
public interface DeviceInfoDao {



    void insertOrUpdate(DeviceInfoEntity deviceInfo);


    DeviceInfoEntity getDeviceInfo(Integer deviceId);
}
