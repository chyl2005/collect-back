package com.mm.back.common;

import com.mm.back.entity.*;
import com.mm.back.vo.DeviceConfigVo;
import com.mm.back.vo.DeviceInfoVo;
import com.mm.back.vo.DeviceRecordVo;
import com.mm.back.vo.UserVo;

/**
 * Author:chyl2005
 * Date:17/6/14
 * Time:13:43
 * Desc:描述该类的作用
 */
public class ConvertUtils {

    private ConvertUtils() {
    }

    public static UserVo parseToUserResponse(UserEntity userEntity) {
        UserVo userVo = new UserVo();
        userVo.setUserId(userEntity.getId());
        userVo.setUserName(userEntity.getUserName());
        userVo.setTrueName(userEntity.getTrueName());
        userVo.setGmtCreated(userEntity.getGmtCreated());
        userVo.setGmtModified(userEntity.getGmtModified());
        userVo.setIsDel(userEntity.getIsDel());
        return userVo;
    }

    public static DeviceInfoVo parseToDeviceInfoVo(DeviceInfoEntity deviceInfoEntity) {
        DeviceInfoVo deviceInfo = new DeviceInfoVo();
        deviceInfo.setDeviceId(deviceInfoEntity.getId());
        deviceInfo.setDeviceNum(deviceInfoEntity.getDeviceNum());
        deviceInfo.setGmtCreated(deviceInfoEntity.getGmtCreated());
        deviceInfo.setGmtModified(deviceInfoEntity.getGmtModified());
        deviceInfo.setCityId(deviceInfoEntity.getCityId());
        deviceInfo.setCityName(deviceInfoEntity.getCityName());
        deviceInfo.setProvinceId(deviceInfoEntity.getProvinceId());
        deviceInfo.setProvinceName(deviceInfoEntity.getProvinceName());
        deviceInfo.setDeviceType(deviceInfoEntity.getDeviceType());
        deviceInfo.setLatitude(deviceInfoEntity.getLatitude());
        deviceInfo.setLongitude(deviceInfoEntity.getLongitude());
        return deviceInfo;
    }

    public static DeviceConfigVo parseToDeviceConfigVo(DeviceConfigEntity configEntity) {
        DeviceConfigVo configResponse = new DeviceConfigVo();
        configResponse.setDeviceId(configEntity.getDeviceId());
        configResponse.setPhoneNum1(configEntity.getPhoneNum1());
        configResponse.setPhoneNum2(configEntity.getPhoneNum2());
        configResponse.setWakeupTime1(configEntity.getWakeupTime1());
        configResponse.setWakeupTime2(configEntity.getWakeupTime2());
        configResponse.setSensorDepth(configEntity.getSensorDepth());
        configResponse.setSurfaceHigh(configEntity.getSurfaceHigh());
        configResponse.setServerPort(configEntity.getServerPort());
        configResponse.setServerIp(configEntity.getServerIp());
        configResponse.setGmtCreated(configEntity.getGmtCreated());
        configResponse.setGmtModified(configEntity.getGmtModified());
        configResponse.setIsDel(configEntity.getIsDel());
        configResponse.setLinearCoefficient(configEntity.getLinearCoefficient());
        configResponse.setSoftwareVersion(configEntity.getSoftwareVersion());
        configResponse.setSerialNum(configEntity.getSerialNum());
        return configResponse;
    }



    public static DeviceRecordVo parseToDeviceRecordVo(DeviceRecordEntity recordEntity) {
        DeviceRecordVo recordVo = new DeviceRecordVo();
        recordVo.setDeviceId(recordEntity.getDeviceId());
        recordVo.setDatekey(recordEntity.getDatekey());
        recordVo.setSensorDepth(recordEntity.getSensorDepth());
        recordVo.setSurfaceHigh(recordEntity.getSurfaceHigh());
        recordVo.setWaterDepth(recordEntity.getWaterDepth());
        recordVo.setWaterHigh(recordEntity.getWaterHigh());
        recordVo.setAirTemperature(recordEntity.getAirTemperature());
        recordVo.setWaterTemperature(recordEntity.getWaterTemperature());
        recordVo.setVoltage(recordEntity.getVoltage());
        recordVo.setSignal(recordEntity.getSignal());
        recordVo.setCollectTime(recordEntity.getCollectTime());
        recordVo.setGmtCreated(recordEntity.getGmtCreated());
        recordVo.setGmtModified(recordEntity.getGmtModified());
        recordVo.setIsDel(recordEntity.getIsDel());
        return recordVo;
    }
}
