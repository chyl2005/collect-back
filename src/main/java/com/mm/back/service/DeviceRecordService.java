package com.mm.back.service;

import java.util.List;
import com.mm.back.common.AoData;
import com.mm.back.model.DeviceRecordResponse;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:07
 * Desc:描述该类的作用
 */
public interface DeviceRecordService {

    AoData<List<DeviceRecordResponse>> getRecords(Integer deviceId, Integer startTime, Integer endTime);
}
