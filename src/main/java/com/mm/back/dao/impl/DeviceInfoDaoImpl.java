package com.mm.back.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    }

    @Override
    public DeviceInfoEntity getDeviceInfo(Integer deviceId) {
        DeviceInfoEntity entity = this.get(DeviceInfoEntity.class, deviceId);
        return entity;
    }

    @Override
    public DeviceInfoEntity getDeviceByDeviceNum(String deviceNum) {
        if (StringUtils.isBlank(deviceNum)) {
            return null;
        }
        return this.findFirst("from DeviceInfoEntity where deviceNum=?", deviceNum);

    }

    @Override
    public AoData<List<DeviceInfoEntity>> getDeviceInfos(List<Integer> deviceIds) {
        if (CollectionUtils.isEmpty(deviceIds)) {
            return new AoData<>();
        }
        StringBuilder hql = new StringBuilder();
        hql.append(" from ").append(DeviceInfoEntity.class.getSimpleName()).append(" where 1=1 ");
        hql.append(" and  id in(:deviceId)");
        hql.append(" and  isDel=:isDel");

        Map<String, Object> params = new HashMap<>();
        params.put("deviceId", deviceIds);
        params.put("isDel", DeleteStatusEnum.NOT_DEL.getCode());

        AoData aoData = this.findPage(null, hql.toString(), params);
        return aoData;
    }

    @Override
    public void updateDelStatus(Integer deviceId, Integer isDel) {
        DeviceInfoEntity deviceInfoEntity = this.get(DeviceInfoEntity.class, deviceId);
        if (deviceInfoEntity != null) {
            deviceInfoEntity.setIsDel(isDel);
            deviceInfoEntity.setGmtModified(new Date());
        }
    }
}
