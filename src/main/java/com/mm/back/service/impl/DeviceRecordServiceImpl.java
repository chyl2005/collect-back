package com.mm.back.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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
import com.mm.back.vo.DeviceRecordVo;
import com.mm.common.utils.DateUtils;

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
    public void insert(DeviceRecordDto deviceRecordDto) {
        String deviceNum = deviceRecordDto.getDeviceNum();
        DeviceInfoEntity deviceInfo = deviceInfoDao.getDeviceByDeviceNum(deviceNum);
        if (deviceInfo != null) {
            deviceRecordDto.setDeviceId(deviceInfo.getId());
        }
        recordDao.insert(parseToDeviceRecordEntity(deviceRecordDto));
    }

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

    @Override
    public Workbook download(Integer deviceId, Integer startTime, Integer endTime) {
        List<DeviceRecordVo> records = this.getRecords(deviceId, startTime, endTime);

        Workbook workbook = new SXSSFWorkbook(100);//内存中保留 100 条数据，以免内存溢出，其余写入 硬盘
        Sheet sheet = workbook.createSheet("上传记录");
        if (CollectionUtils.isEmpty(records)) {
            return workbook;
        }
        //设置title
        Integer rowNum = 0;
        Integer titleCellNum = 0;
        Row titleRow = sheet.createRow(rowNum++);
        titleRow.createCell(titleCellNum++).setCellValue("设备ID");
        titleRow.createCell(titleCellNum++).setCellValue("设备硬件编号");
        titleRow.createCell(titleCellNum++).setCellValue("日期");
        titleRow.createCell(titleCellNum++).setCellValue("上传时间");
        titleRow.createCell(titleCellNum++).setCellValue("地面高程(米)");
        titleRow.createCell(titleCellNum++).setCellValue("传感器深度(米)");
        titleRow.createCell(titleCellNum++).setCellValue("水位标高(米)");
        titleRow.createCell(titleCellNum++).setCellValue("水位埋深(米)");
        titleRow.createCell(titleCellNum++).setCellValue("气温(\u2103)");
        titleRow.createCell(titleCellNum++).setCellValue("水温(\u2103)");
        titleRow.createCell(titleCellNum++).setCellValue("电压(V)");
        titleRow.createCell(titleCellNum++).setCellValue("信号");
        for (DeviceRecordVo record : records) {
            Row row = sheet.createRow(rowNum++);
            Integer cellNum = 0;
            row.createCell(cellNum++).setCellValue(record.getDeviceId());
            row.createCell(cellNum++).setCellValue(record.getDeviceNum());
            row.createCell(cellNum++).setCellValue(record.getDatekey());
            row.createCell(cellNum++).setCellValue(DateUtils.getDateformat(record.getCollectTime(), DateUtils.YMD_HMS_FORMAT));
            row.createCell(cellNum++).setCellValue(record.getSurfaceHigh() != null ? record.getSurfaceHigh().doubleValue() : 0.00);
            row.createCell(cellNum++).setCellValue(record.getSensorDepth() != null ? record.getSensorDepth().doubleValue() : 0.00);
            row.createCell(cellNum++).setCellValue(record.getWaterHigh() != null ? record.getWaterHigh().doubleValue() : 0.00);
            row.createCell(cellNum++).setCellValue(record.getWaterDepth() != null ? record.getWaterDepth().doubleValue() : 0.00);
            row.createCell(cellNum++).setCellValue(record.getAirTemperature() != null ? record.getAirTemperature().doubleValue() : 0.00);
            row.createCell(cellNum++).setCellValue(record.getWaterTemperature() != null ? record.getWaterTemperature().doubleValue() : 0.00);
            row.createCell(cellNum++).setCellValue(record.getVoltage() != null ? record.getVoltage().doubleValue() : 0.00);
            row.createCell(cellNum++).setCellValue(record.getSignal() != null ? record.getSignal().doubleValue() : 0.00);
        }

        return workbook;
    }

    private DeviceRecordEntity parseToDeviceRecordEntity(DeviceRecordDto deviceRecordDto) {
        DeviceRecordEntity record = new DeviceRecordEntity();
        String collectTime = deviceRecordDto.getCollectTime();
        Date collectDate = new Date();
        if (StringUtils.isNotBlank(collectTime)) {
            collectDate = DateUtils.getDate(collectTime, DateUtils.YMD_HMS_FORMAT_DIAS);
        }
        record.setDeviceNum(deviceRecordDto.getDeviceNum());
        record.setDeviceId(deviceRecordDto.getDeviceId());
        record.setDatekey(DateUtils.getDatekey(collectDate));
        String yyyyMMddHHmm = DateUtils.getDateformat(collectDate, DateUtils.yyyyMMddHHmmss);
        record.setMinutes(Long.valueOf(yyyyMMddHHmm));
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
