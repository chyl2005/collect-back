package com.mm.back.service;

import java.util.List;
import com.mm.back.common.AoData;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.model.DeviceInfoResponse;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:07
 * Desc:描述该类的作用
 */
public interface DeviceInfoService {

    /**
     * 获取设备信息
     * @param deviceId
     * @return
     */
    DeviceInfoResponse getDeviceInfo(Integer deviceId);



    void insertOrUpdate(DeviceInfoEntity deviceInfo);


    AoData<List<DeviceInfoResponse>> getDeviceInfos();
}
