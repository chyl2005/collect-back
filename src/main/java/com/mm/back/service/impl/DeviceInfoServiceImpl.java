package com.mm.back.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mm.back.common.AoData;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.model.DeviceInfoResponse;
import com.mm.back.service.DeviceInfoService;

/**
 * Author:chyl2005
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

    @Override
    public void insertOrUpdate(DeviceInfoEntity deviceInfo) {
        this.deviceInfoDao.insertOrUpdate(deviceInfo);
    }

    @Override
    public AoData<List<DeviceInfoResponse>> getDeviceInfos() {
        AoData<List<DeviceInfoEntity>> aoData = this.deviceInfoDao.getDeviceInfos();
        List<DeviceInfoEntity> deviceInfos = aoData.getDatas();
        ArrayList<DeviceInfoResponse> responses = new ArrayList<>();
        for (DeviceInfoEntity deviceInfo : deviceInfos) {
            responses.add(ConvertUtils.parseToDeviceInfoResponse(deviceInfo));
        }
        AoData<List<DeviceInfoResponse>> result = new AoData<>();
        result.setiDisplayLength(aoData.getiDisplayLength());
        result.setiTotalRecords(aoData.getiTotalRecords());
        result.setiTotalDisplayRecords(aoData.getiTotalDisplayRecords());
        result.setiDisplayStart(aoData.getiDisplayStart());
        result.setDatas(responses);
        return result;
    }
}
