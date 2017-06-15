package com.mm.back.service;

import com.mm.back.model.DeviceInfoResponse;

/**
 * Author:chenyanlong@meituan.com
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
}
