package com.mm.back.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.dto.DeviceInfoDto;
import com.mm.back.vo.DeviceInfoVo;
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
    public DeviceInfoVo getDeviceInfo(Integer deviceId) {
        DeviceInfoEntity deviceInfo = deviceInfoDao.getDeviceInfo(deviceId);
        DeviceInfoVo deviceInfoDto = ConvertUtils.parseToDeviceInfoVo(deviceInfo);
        return deviceInfoDto;
    }

    /**
     *
     * @param deviceInfoDto
     * @return  设备ID
     */
    @Transactional
    @Override
    public Integer insertOrUpdate(DeviceInfoDto deviceInfoDto) {
        DeviceInfoEntity deviceInfo = this.deviceInfoDao.getDeviceByDeviceNum(deviceInfoDto.getDeviceNum());
        if (deviceInfo == null) {
            deviceInfo=new DeviceInfoEntity();
        }
        deviceInfo.setDeviceNum(deviceInfoDto.getDeviceNum());
        deviceInfo.setDeviceType(deviceInfoDto.getDeviceType());
        insertOrUpdate(deviceInfo);
        return deviceInfo.getId();
    }

    @Override
    public void insertOrUpdate(DeviceInfoEntity deviceInfo) {
        this.deviceInfoDao.insertOrUpdate(deviceInfo);
    }

    @Override
    public AoData<List<DeviceInfoVo>> getDeviceInfos() {
        AoData<List<DeviceInfoEntity>> aoData = this.deviceInfoDao.getDeviceInfos();
        List<DeviceInfoEntity> deviceInfos = aoData.getDatas();
        ArrayList<DeviceInfoVo> responses = new ArrayList<>();
        for (DeviceInfoEntity deviceInfo : deviceInfos) {
            responses.add(ConvertUtils.parseToDeviceInfoVo(deviceInfo));
        }
        AoData<List<DeviceInfoVo>> result = new AoData<>();
        result.setiDisplayLength(aoData.getiDisplayLength());
        result.setiTotalRecords(aoData.getiTotalRecords());
        result.setiTotalDisplayRecords(aoData.getiTotalDisplayRecords());
        result.setiDisplayStart(aoData.getiDisplayStart());
        result.setDatas(responses);
        return result;
    }

    @Override
    public void updateDelStatus(Integer deviceId, Integer isDel) {
        deviceInfoDao.updateDelStatus(deviceId,isDel);
    }
}
