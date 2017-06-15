package com.mm.back.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mm.back.common.AoData;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceRecordDao;
import com.mm.back.entity.DeviceRecordEntity;
import com.mm.back.model.DeviceRecordResponse;
import com.mm.back.service.DeviceRecordService;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:18
 * Desc:描述该类的作用
 */
@Service
public class DeviceRecordServiceImpl implements DeviceRecordService {

    @Autowired
    private DeviceRecordDao recordDao;

    @Override
    public AoData<List<DeviceRecordResponse>> getRecords(Integer deviceId, Integer startTime, Integer endTime) {
        AoData<List<DeviceRecordEntity>> pageRecords = recordDao.getPageRecords(deviceId, startTime, endTime);
        AoData<List<DeviceRecordResponse>> aoData = new AoData<>();
        aoData.setiDisplayLength(pageRecords.getiDisplayLength());
        aoData.setiDisplayStart(pageRecords.getiDisplayStart());
        aoData.setiTotalDisplayRecords(pageRecords.getiTotalDisplayRecords());
        aoData.setiTotalRecords(pageRecords.getiTotalRecords());
        List<DeviceRecordResponse> responses = new ArrayList<>();
        for (DeviceRecordEntity record : pageRecords.getDatas()) {
            responses.add(ConvertUtils.parseToDeviceRecordResponse(record));
        }
        aoData.setDatas(responses);
        return aoData;
    }
}
