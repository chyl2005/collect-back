package com.mm.back.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.constants.CommandEnum;
import com.mm.back.constants.DeviceTypeEnum;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.dto.*;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.netty.ServerHandler;
import com.mm.back.service.*;
import com.mm.back.utils.JsonUtils;
import com.mm.back.vo.DeviceSettingVo;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:12:32
 * Desc:描述该类的作用
 */
@Service
public class HandlerMessageServiceImpl implements HandlerMessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerMessageServiceImpl.class);
    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private DeviceRecordService deviceRecordService;
    @Autowired
    private DeviceSettingService settingService;

    @Autowired
    private DeviceUploadSettingService uploadSettingService;

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    /**
     * 处理客户端返回的数据
     *
     * @param message
     * @param address
     * @return
     */
    @Override
    @Transactional
    public List<CommandEnum> handlerMessage(String message, String address) {
        BaseData baseData = JsonUtils.json2Object(message, BaseData.class);
        Integer commandNum = baseData.getCommandNum();
        if (CommandEnum.QUERY_PARAM.getCode().equals(commandNum)) {
            DeviceSettingData data = JsonUtils.json2Object(message, DeviceSettingData.class);
            DeviceSettingDto deviceSettingDto = data.getData();
            LOGGER.info("HandlerMessageService.handlerMessage deviceConfigDto={} ", JsonUtils.object2Json(deviceSettingDto));
            //保存设备基础信息
            Integer deviceId = deviceInfoService.insertOrUpdate(parseToDeviceInfoDto(deviceSettingDto));
            LOGGER.info("HandlerMessageService.handlerMessage deviceId={} ", deviceId);
            //保存设备配置信息
            deviceSettingDto.setDeviceId(deviceId);
            uploadSettingService.insertOrUpdate(deviceSettingDto);
            //后台配置数据填充
            settingService.saveIfNotExist(deviceSettingDto);
            //地址到 设备号映射
            ServerHandler.addressToDeviceNumMap.put(address, deviceSettingDto.getDeviceNum());

        } else if (CommandEnum.QUERY_NEW_DATA1.getCode().equals(commandNum)
                || CommandEnum.QUERY_NEW_DATA2.getCode().equals(commandNum)
                || CommandEnum.QUERY_DATA.getCode().equals(commandNum)) {
            DeviceRecordData data = JsonUtils.json2Object(message, DeviceRecordData.class);
            DeviceRecordDto deviceRecordDto = data.getData();
            LOGGER.info("HandlerMessageService.handlerMessage deviceRecordDto={} ", JsonUtils.object2Json(deviceRecordDto));
            deviceRecordService.insertOrUpdate(deviceRecordDto);
        } else if (CommandEnum.QUERY_STORE_INFO.getCode().equals(commandNum)) {
            BaseData<StoreInfoDto> base = JsonUtils.json2Object(message, BaseData.class);
            StoreInfoDto StoreInfoDto = base.getData();
        }

        return null;

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<String> sendMessage(String deviceNum) {
        DeviceInfoEntity deviceInfo = deviceInfoDao.getDeviceByDeviceNum(deviceNum);
        if (deviceInfo == null) {
            return null;
        }
        //获取后台配置信息
        DeviceSettingVo configInfo = settingService.getSetting(deviceInfo.getId());
        if (configInfo == null) {
            LOGGER.error("HandlerMessageService.sendMessage  没有设备参数信息");
        }
        List<String> setParams = new ArrayList<>();
        if (configInfo.getSensorDepth() != null) {
            setParams.add(CommandEnum.SET_SENSOR_DEPTH.getCommond() + CommandEnum.SPILT + configInfo.getSensorDepth());
        }
        if (configInfo.getSurfaceHigh() != null) {
            setParams.add(CommandEnum.SET_SURFACE_HIGH.getCommond() + CommandEnum.SPILT + configInfo.getSurfaceHigh());
        }
        if (configInfo.getLinearCoefficient() != null) {
            setParams.add(CommandEnum.SET_LINE.getCommond() + CommandEnum.SPILT + configInfo.getLinearCoefficient());
        }
        if (configInfo.getPhoneNum1() != null) {
            setParams.add(CommandEnum.SET_PHONE1.getCommond() + CommandEnum.SPILT + configInfo.getPhoneNum1());
        }
        if (configInfo.getPhoneNum2() != null) {
            setParams.add(CommandEnum.SET_PHONE2.getCommond() + CommandEnum.SPILT + configInfo.getPhoneNum2());
        }
        if (configInfo.getWakeupTime1() != null && configInfo.getWakeupTime2() != null) {
            setParams.add(CommandEnum.SET_WAKEUP.getCommond().replace("@wakeupTime1", configInfo.getWakeupTime1()).replace("@wakeupTime2", configInfo.getWakeupTime2()));
        }

        if (configInfo.getServerIp() != null) {
            setParams.add(CommandEnum.SET_IP.getCommond() + CommandEnum.SPILT + configInfo.getServerIp());
        }
        if (configInfo.getSerialNum() != null) {
            setParams.add(CommandEnum.SET_WELL_NUM.getCommond() + CommandEnum.SPILT + configInfo.getSerialNum());
        }

        setParams.add(CommandEnum.QUERY_PARAM.getCommond());

        LOGGER.info("HandlerMessageService.sendMessage   sendMsg={}", JsonUtils.object2Json(setParams));
        return setParams;
    }

    private DeviceInfoDto parseToDeviceInfoDto(DeviceSettingDto deviceSettingDto) {
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDto.setDeviceNum(deviceSettingDto.getDeviceNum());
        deviceInfoDto.setDeviceType(DeviceTypeEnum.WELL.getCode());
        deviceInfoDto.setSerialNum(deviceSettingDto.getWellNum());
        return deviceInfoDto;

    }

}
