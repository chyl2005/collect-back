package com.mm.back.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.dao.DeviceUploadSettingDao;
import com.mm.back.dao.RelUserDeviceDao;
import com.mm.back.dto.DeviceInfoDto;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.entity.DeviceUploadSettingEntity;
import com.mm.back.entity.RelUserDeviceEntity;
import com.mm.back.netty.ServerHandler;
import com.mm.back.service.DeviceInfoService;
import com.mm.back.vo.DeviceInfoVo;

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

    @Autowired
    private RelUserDeviceDao relUserDeviceDao;
    @Autowired
    private DeviceUploadSettingDao deviceUploadSettingDao;

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
     * @param deviceInfoDto
     * @return 设备ID
     */
    @Transactional
    @Override
    public Integer insertOrUpdate(DeviceInfoDto deviceInfoDto) {
        DeviceInfoEntity deviceInfo = this.deviceInfoDao.getDeviceByDeviceNum(deviceInfoDto.getDeviceNum());
        if (deviceInfo == null) {
            deviceInfo = new DeviceInfoEntity();
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
    public AoData<List<DeviceInfoVo>> getDeviceInfos(Integer userId) {

        List<RelUserDeviceEntity> userDevices = relUserDeviceDao.getUserDevices(userId);
        List<Integer> deviceIds = userDevices.stream().map(userDevice -> userDevice.getDeviceId()).collect(Collectors.toList());
        AoData<List<DeviceInfoEntity>> aoData = this.deviceInfoDao.getDeviceInfos(deviceIds);
        List<DeviceInfoEntity> deviceInfos = aoData.getDatas();
        ArrayList<DeviceInfoVo> responses = new ArrayList<>();
        Map<String, String> addressToDeviceNumMap = ServerHandler.addressToDeviceNumMap;
        HashMap<String, String> deviceToAddressMap = new HashMap<>();
        for (Map.Entry<String, String> entry : addressToDeviceNumMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                deviceToAddressMap.put(entry.getValue(), entry.getKey());
            }
        }

        List<DeviceUploadSettingEntity> settingEntities = deviceUploadSettingDao.getAllSetting();
        Map<Integer, Integer> settingMap = settingEntities.stream().collect(Collectors.toMap(set -> set.getDeviceId(), set -> set.getSerialNum()));

        for (DeviceInfoEntity deviceInfo : deviceInfos) {
            DeviceInfoVo deviceInfoVo = ConvertUtils.parseToDeviceInfoVo(deviceInfo);
            deviceInfoVo.setSerialNum(settingMap.get(deviceInfoVo.getDeviceId()));
            if (addressToDeviceNumMap.containsValue(deviceInfo.getDeviceNum())) {
                deviceInfoVo.setOnline(1);
            } else {
                deviceInfoVo.setOnline(0);
            }
            deviceInfoVo.setClientIP(deviceToAddressMap.get(deviceInfo.getDeviceNum()));
            responses.add(deviceInfoVo);
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
        deviceInfoDao.updateDelStatus(deviceId, isDel);
    }
}
