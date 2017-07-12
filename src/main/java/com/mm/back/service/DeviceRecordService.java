package com.mm.back.service;

import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import com.mm.back.common.AoData;
import com.mm.back.dto.DeviceRecordDto;
import com.mm.back.entity.DeviceRecordEntity;
import com.mm.back.vo.DeviceRecordVo;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:07
 * Desc:描述该类的作用
 */
public interface DeviceRecordService {

    void insert(DeviceRecordDto deviceRecordDto);

    void insertOrUpdate(DeviceRecordDto deviceRecordDto);

    List<DeviceRecordVo> getRecords(Integer deviceId, Integer startTime, Integer endTime);

    AoData<List<DeviceRecordVo>> getPageRecords(Integer deviceId, Integer startTime, Integer endTime);

    Workbook download(Integer deviceId, Integer startTime, Integer endTime);
}
