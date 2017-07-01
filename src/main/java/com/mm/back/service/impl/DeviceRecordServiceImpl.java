package com.mm.back.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.common.ConvertUtils;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.dao.DeviceRecordDao;
import com.mm.back.dto.DeviceRecordDto;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.entity.DeviceRecordEntity;
import com.mm.back.service.DeviceRecordService;
import com.mm.back.utils.DateUtils;
import com.mm.back.vo.DeviceRecordVo;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:18
 * Desc:描述该类的作用
 */
@Service
public class DeviceRecordServiceImpl implements DeviceRecordService {

    @Autowired
    private DeviceRecordDao recordDao;
    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Transactional
    @Override
    public void insertOrUpdate(DeviceRecordDto deviceRecordDto) {
        String deviceNum = deviceRecordDto.getDeviceNum();
        DeviceInfoEntity deviceInfo = deviceInfoDao.getDeviceByDeviceNum(deviceNum);
        if (deviceInfo != null) {
            deviceRecordDto.setDeviceId(deviceInfo.getId());
        }
        recordDao.insertOrUpdate(parseToDeviceRecordEntity(deviceRecordDto));
    }

    @Override
    public List<DeviceRecordVo> getRecords(Integer deviceId, Integer startTime, Integer endTime) {
        List<DeviceRecordEntity> records = recordDao.getRecords(deviceId, startTime, endTime);
        List<DeviceRecordVo> responses = new ArrayList<>();
        for (DeviceRecordEntity record : records) {
            responses.add(ConvertUtils.parseToDeviceRecordVo(record));
        }
        return responses;
    }

    @Override
    public AoData<List<DeviceRecordVo>> getPageRecords(Integer deviceId, Integer startTime, Integer endTime) {
        AoData<List<DeviceRecordEntity>> pageRecords = recordDao.getPageRecords(deviceId, startTime, endTime);
        AoData<List<DeviceRecordVo>> aoData = new AoData<>();
        aoData.setiDisplayLength(pageRecords.getiDisplayLength());
        aoData.setiDisplayStart(pageRecords.getiDisplayStart());
        aoData.setiTotalDisplayRecords(pageRecords.getiTotalDisplayRecords());
        aoData.setiTotalRecords(pageRecords.getiTotalRecords());
        List<DeviceRecordVo> responses = new ArrayList<>();
        for (DeviceRecordEntity record : pageRecords.getDatas()) {
            responses.add(ConvertUtils.parseToDeviceRecordVo(record));
        }
        aoData.setDatas(responses);
        return aoData;
    }

    private DeviceRecordEntity parseToDeviceRecordEntity(DeviceRecordDto deviceRecordDto) {
        DeviceRecordEntity record = new DeviceRecordEntity();
        String collectTime = deviceRecordDto.getCollectTime();
        Date collectDate = DateUtils.getDate(collectTime, DateUtils.YMD_HMS_FORMAT_DIAS);
        record.setDeviceNum(deviceRecordDto.getDeviceNum());
        record.setDeviceId(deviceRecordDto.getDeviceId());
        record.setDatekey(DateUtils.getDatekey(collectDate));
        record.setCollectTime(collectDate);

        record.setSensorDepth(deviceRecordDto.getSensorDepth());
        record.setSurfaceHigh(deviceRecordDto.getSurfaceHigh());

        record.setWaterDepth(deviceRecordDto.getWaterDepth());
        record.setWaterHigh(deviceRecordDto.getWaterHigh());

        record.setAirTemperature(deviceRecordDto.getAirTemperature());
        record.setWaterTemperature(deviceRecordDto.getWaterTemperature());

        record.setVoltage(deviceRecordDto.getVoltage());
        record.setSignal(deviceRecordDto.getSignal());
        return record;

    }
}
