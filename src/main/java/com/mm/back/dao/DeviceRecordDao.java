package com.mm.back.dao;

import java.util.Date;
import java.util.List;
import com.mm.back.common.AoData;
import com.mm.back.entity.DeviceRecordEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:11:16
 * Desc:描述该类的作用
 */
public interface DeviceRecordDao {

    void insert(DeviceRecordEntity recordEntity);
    void insertOrUpdate(DeviceRecordEntity recordEntity);

    void del(Date startTime ,Date endTime);


    AoData<List<DeviceRecordEntity>> getPageRecords(Integer deviceId, Integer startTime, Integer endTime);



    List<DeviceRecordEntity> getRecords(Integer deviceId, Integer startTime, Integer endTime);
}
