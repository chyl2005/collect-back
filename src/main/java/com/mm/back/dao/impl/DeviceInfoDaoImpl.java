package com.mm.back.dao.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.mm.back.common.AoData;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.entity.DeviceInfoEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:12:17
 * Desc:描述该类的作用
 */
@Repository
public class DeviceInfoDaoImpl extends BaseDaoImpl<DeviceInfoEntity> implements DeviceInfoDao {

    @Override
    public void insertOrUpdate(DeviceInfoEntity deviceInfo) {
        deviceInfo.setGmtModified(new Date());
        deviceInfo.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
        if (deviceInfo.getId() != null) {
            this.update(deviceInfo);
        } else {
            deviceInfo.setGmtCreated(new Date());
            this.save(deviceInfo);
        }
        this.saveOrUpdate(deviceInfo);

    }

    @Override
    public DeviceInfoEntity getDeviceInfo(Integer deviceId) {
        DeviceInfoEntity entity = this.get(DeviceInfoEntity.class, deviceId);
        return entity;
    }

    @Override
    public AoData<List<DeviceInfoEntity>> getDeviceInfos() {
        AoData aoData = this.findPage(null, "from DeviceInfoEntity ");
        return aoData;
    }
}
