package com.mm.back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.model.DeviceInfoResponse;
import com.mm.back.service.DeviceInfoService;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/15
 * Time:13:21
 * Desc:描述该类的作用
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {


    @Autowired
    private DeviceInfoDao deviceInfoDao;
    /**
     * 获取设备信息
     *
     * @param deviceId
     * @return
     */
    @Override
    public DeviceInfoResponse getDeviceInfo(Integer deviceId) {
        DeviceInfoEntity deviceInfo = deviceInfoDao.getDeviceInfo(deviceId);
        DeviceInfoResponse deviceInfoResponse = ConvertUtils.parseToDeviceInfoResponse(deviceInfo);
        return deviceInfoResponse;
    }
}
