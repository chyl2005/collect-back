package com.mm.back.common;

import com.mm.back.entity.DeviceConfigEntity;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.entity.DeviceRecordEntity;
import com.mm.back.entity.UserEntity;
import com.mm.back.model.DeviceConfigResponse;
import com.mm.back.model.DeviceInfoResponse;
import com.mm.back.model.DeviceRecordResponse;
import com.mm.back.model.UserResponse;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/14
 * Time:13:43
 * Desc:描述该类的作用
 */
public class ConvertUtils {

    private ConvertUtils() {
    }

    public static UserResponse parseToUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userEntity.getId());
        userResponse.setUserName(userEntity.getUserName());
        userResponse.setTrueName(userEntity.getTrueName());
        userResponse.setGmtCreated(userEntity.getGmtCreated());
        userResponse.setGmtModified(userEntity.getGmtModified());
        userResponse.setIsDel(userEntity.getIsDel());
        return userResponse;
    }

    public static DeviceInfoResponse parseToDeviceInfoResponse(DeviceInfoEntity deviceInfoEntity) {
        DeviceInfoResponse deviceInfo = new DeviceInfoResponse();
        deviceInfo.setDeviceId(deviceInfoEntity.getId());
        deviceInfo.setDeviceNum(deviceInfoEntity.getDeviceNum());
        deviceInfo.setGmtCreated(deviceInfoEntity.getGmtCreated());
        deviceInfo.setGmtModified(deviceInfoEntity.getGmtModified());
        deviceInfo.setCityId(deviceInfoEntity.getCityId());
        deviceInfo.setCityName(deviceInfoEntity.getCityName());
        deviceInfo.setProvinceId(deviceInfoEntity.getProvinceId());
        deviceInfo.setProvinceName(deviceInfoEntity.getProvinceName());
        deviceInfo.setDeviceType(deviceInfoEntity.getDeviceType());
        deviceInfo.setSerialNum(deviceInfoEntity.getSerialNum());
        deviceInfo.setLatitude(deviceInfoEntity.getLatitude());
        deviceInfo.setLongitude(deviceInfoEntity.getLongitude());
        return deviceInfo;
    }

    public static DeviceConfigResponse parseToDeviceConfigResponse(DeviceConfigEntity configEntity) {
        DeviceConfigResponse configResponse = new DeviceConfigResponse();
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
        return configResponse;
    }

    public static DeviceRecordResponse parseToDeviceRecordResponse(DeviceRecordEntity recordEntity) {
        DeviceRecordResponse recordResponse = new DeviceRecordResponse();
        recordResponse.setDeviceId(recordEntity.getDeviceId());
        recordResponse.setDatekey(recordEntity.getDatekey());
        recordResponse.setAirTemperature(recordEntity.getAirTemperature());
        recordResponse.setWaterDepth(recordEntity.getWaterDepth());
        recordResponse.setWaterHigh(recordEntity.getWaterHigh());
        recordResponse.setWaterTemperature(recordEntity.getWaterTemperature());
        recordResponse.setGmtCreated(recordEntity.getGmtCreated());
        recordResponse.setGmtModified(recordEntity.getGmtModified());
        recordResponse.setIsDel(recordEntity.getIsDel());
        return recordResponse;
    }
}
