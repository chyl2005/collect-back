package com.mm.back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceConfigDao;
import com.mm.back.entity.DeviceConfigEntity;
import com.mm.back.model.DeviceConfigResponse;
import com.mm.back.service.DeviceConfigService;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:19
 * Desc:描述该类的作用
 */
@Service
public class DeviceConfigServiceImpl implements DeviceConfigService {

    @Autowired
    private DeviceConfigDao deviceConfigDao;

    @Override
    public DeviceConfigResponse getConfigInfo(Integer deviceId) {
        DeviceConfigEntity deviceConfig = deviceConfigDao.getDeviceConfig(deviceId);
        DeviceConfigResponse configResponse = ConvertUtils.parseToDeviceConfigResponse(deviceConfig);
        return configResponse;
    }
}
