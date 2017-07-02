package com.mm.back.dao.impl;

import java.util.Date;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.DeviceSettingDao;
import com.mm.back.entity.DeviceSettingEntity;
import com.mm.back.entity.DeviceUploadSettingEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:11:23
 * Desc:描述该类的作用
 */
@Repository
public class DeviceSettingDaoImpl extends BaseDaoImpl<DeviceSettingEntity> implements DeviceSettingDao {

    @Override
    public void insert(DeviceSettingEntity setting) {
        setting.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
        setting.setGmtCreated(new Date());
        setting.setGmtModified(new Date());
        this.save(setting);
    }

    /**
     * @param deviceConfig
     */
    @Transactional
    @Override
    public void insertOrUpdate(DeviceSettingEntity deviceConfig) {
        DeviceSettingEntity device = this.findFirst("from DeviceSettingEntity where deviceId=?", deviceConfig.getDeviceId());
        if (device != null) {
            device.setPhoneNum1(deviceConfig.getPhoneNum1());
            device.setPhoneNum2(deviceConfig.getPhoneNum2());
            device.setSensorDepth(deviceConfig.getSensorDepth());
            device.setServerIp(deviceConfig.getServerIp());
            device.setServerPort(deviceConfig.getServerPort());
            device.setSurfaceHigh(deviceConfig.getSurfaceHigh());
            device.setWakeupTime1(deviceConfig.getWakeupTime1());
            device.setWakeupTime2(deviceConfig.getWakeupTime2());
            device.setGmtModified(new Date());
            device.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            this.update(device);
        } else {
            deviceConfig.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            deviceConfig.setGmtCreated(new Date());
            deviceConfig.setGmtModified(new Date());
            this.save(deviceConfig);
        }

    }

    @Override
    public DeviceSettingEntity getSetting(Integer deviceId) {
        return this.findFirst("from DeviceSettingEntity where deviceId=?", deviceId);
    }
}
