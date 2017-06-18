package com.mm.back.dao;

import java.util.List;
import com.mm.back.common.AoData;
import com.mm.back.entity.DeviceInfoEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:11:15
 * Desc:设备基础信息
 */
public interface DeviceInfoDao {

    void insertOrUpdate(DeviceInfoEntity deviceInfo);

    DeviceInfoEntity getDeviceInfo(Integer deviceId);

    AoData<List<DeviceInfoEntity>> getDeviceInfos();
}
