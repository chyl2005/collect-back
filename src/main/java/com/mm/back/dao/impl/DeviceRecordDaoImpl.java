package com.mm.back.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.mm.back.common.AoData;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.DeviceRecordDao;
import com.mm.back.entity.DeviceRecordEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:12:27
 * Desc:描述该类的作用
 */
@Repository
public class DeviceRecordDaoImpl extends BaseDaoImpl<DeviceRecordEntity> implements DeviceRecordDao {

    @Override
    public void insert(DeviceRecordEntity recordEntity) {
        recordEntity.setGmtModified(new Date());
        recordEntity.setGmtCreated(new Date());
        recordEntity.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
        this.save(recordEntity);
    }

    @Override
    public void insertOrUpdate(DeviceRecordEntity recordEntity) {
        DeviceRecordEntity oldEntity = this.findFirst("from DeviceRecordEntity where deviceNum=? and minutes=?"
                , recordEntity.getDeviceNum(), recordEntity.getMinutes());
        if (oldEntity != null) {
            oldEntity.setDeviceId(recordEntity.getDeviceId());

            if (recordEntity.getSensorDepth() != null) {
                oldEntity.setSensorDepth(recordEntity.getSensorDepth());
            }
            if (recordEntity.getSurfaceHigh() != null) {
                oldEntity.setSurfaceHigh(recordEntity.getSurfaceHigh());
            }
            if (recordEntity.getWaterDepth() != null) {
                oldEntity.setWaterDepth(recordEntity.getWaterDepth());
            }
            if (recordEntity.getWaterHigh() != null) {
                oldEntity.setWaterHigh(recordEntity.getWaterHigh());
            }
            if (recordEntity.getAirTemperature() != null) {
                oldEntity.setAirTemperature(recordEntity.getAirTemperature());
            }
            if (recordEntity.getWaterTemperature() != null) {
                oldEntity.setWaterTemperature(recordEntity.getWaterTemperature());
            }
            if (recordEntity.getVoltage() != null) {
                oldEntity.setVoltage(recordEntity.getVoltage());
            }
            if (recordEntity.getSignal() != null) {
                oldEntity.setSignal(recordEntity.getSignal());
            }
            oldEntity.setGmtModified(new Date());
            oldEntity.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            this.update(oldEntity);
        } else {
            recordEntity.setGmtModified(new Date());
            recordEntity.setGmtCreated(new Date());
            recordEntity.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            this.save(recordEntity);
        }

    }

    @Override
    public AoData<List<DeviceRecordEntity>> getPageRecords(Integer deviceId, Integer startTime, Integer endTime) {
        Map<String, Object> paras = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append("from DeviceRecordEntity where ");

        paras.put("deviceId", deviceId);
        hql.append(" deviceId=:deviceId");

        paras.put("startTime", startTime);
        paras.put("endTime", endTime);
        hql.append(" and  datekey between  :startTime and :endTime order by minutes");

        return this.findPage(null, hql.toString(), paras);
    }

    @Override
    public List<DeviceRecordEntity> getRecords(Integer deviceId, Integer startTime, Integer endTime) {
        Map<String, Object> paras = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append("from DeviceRecordEntity where ");

        paras.put("deviceId", deviceId);
        hql.append(" deviceId=:deviceId");

        paras.put("startTime", startTime);
        paras.put("endTime", endTime);
        hql.append(" and  datekey between  :startTime and :endTime order by minutes");
        return this.find(hql.toString(), paras);
    }
}
