package com.mm.back.service;

import com.mm.back.entity.DeviceConfigEntity;
import com.mm.back.model.DeviceConfigResponse;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:07
 * Desc:描述该类的作用
 */
public interface DeviceConfigService {


    void insertOrUpdate(DeviceConfigEntity deviceConfig);

    DeviceConfigResponse getConfigInfo(Integer deviceId);
}
