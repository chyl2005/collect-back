package com.mm.back.dao.impl;

import java.util.Date;
import org.springframework.stereotype.Repository;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.DeviceConfigDao;
import com.mm.back.entity.DeviceConfigEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:11:23
 * Desc:描述该类的作用
 */
@Repository
public class DeviceConfigDaoImpl extends BaseDaoImpl<DeviceConfigEntity> implements DeviceConfigDao {

    /**
     * @param deviceConfig
     */
    @Override
    public void insertOrUpdate(DeviceConfigEntity deviceConfig) {
        DeviceConfigEntity device = this.findFirst("from DeviceConfigEntity where deviceId=?", deviceConfig.getDeviceId());
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
            this.save(deviceConfig);
        }

    }

    @Override
    public DeviceConfigEntity getDeviceConfig(Integer deviceId) {
        return this.findFirst("from DeviceConfigEntity where deviceId=?", deviceId);
    }
}
