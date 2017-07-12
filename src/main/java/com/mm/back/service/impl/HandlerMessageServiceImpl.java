package com.mm.back.service.impl;

import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.Config;
import com.mm.back.constants.CommandEnum;
import com.mm.back.constants.DeviceTypeEnum;
import com.mm.back.dao.DeviceInfoDao;
import com.mm.back.dto.*;
import com.mm.back.entity.DeviceInfoEntity;
import com.mm.back.netty.SendDelayMessageService;
import com.mm.back.netty.ServerHandler;
import com.mm.back.service.*;
import com.mm.common.utils.DateUtils;
import com.mm.common.utils.JsonUtils;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:12:32
 * Desc:描述该类的作用
 */
@Service
public class HandlerMessageServiceImpl implements HandlerMessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerMessageServiceImpl.class);
    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private DeviceRecordService deviceRecordService;
    @Autowired
    private DeviceSettingService settingService;

    @Autowired
    private DeviceUploadSettingService uploadSettingService;

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Autowired
    private SendDelayMessageService sendDelayMessageService;
    @Autowired
    private Config config;

    /**
     * 处理客户端返回的数据
     *
     * @param message
     * @param address
     * @return
     */
    @Override
    @Transactional
    public List<CommandEnum> handlerMessage(String message, String address) {
        BaseData baseData = JsonUtils.json2Object(message, BaseData.class);
        Integer commandNum = baseData.getCommandNum();
        if (CommandEnum.QUERY_PARAM.getCode().equals(commandNum)) {
            DeviceSettingData data = JsonUtils.json2Object(message, DeviceSettingData.class);
            DeviceSettingDto deviceSettingDto = data.getData();
            if (StringUtils.isBlank(deviceSettingDto.getUploadTime())) {
                deviceSettingDto.setUploadTime("21/10");
            }
            if (StringUtils.isBlank(deviceSettingDto.getWakeInterval())) {
                deviceSettingDto.setWakeInterval("0030");
            }
            LOGGER.info("HandlerMessageService.handlerMessage deviceConfigDto={} ", JsonUtils.object2Json(deviceSettingDto));
            //保存设备基础信息
            Integer deviceId = deviceInfoService.insertOrUpdate(parseToDeviceInfoDto(deviceSettingDto));
            LOGGER.info("HandlerMessageService.handlerMessage deviceId={} ", deviceId);
            //保存设备配置信息
            deviceSettingDto.setDeviceId(deviceId);
            uploadSettingService.insertOrUpdate(deviceSettingDto);
            //后台配置数据填充
            settingService.saveIfNotExist(deviceSettingDto);
            //地址到 设备号映射
            ServerHandler.addressToDeviceNumMap.put(address, deviceSettingDto.getDeviceNum());

        } else if (CommandEnum.QUERY_NEW_DATA1.getCode().equals(commandNum)
                || CommandEnum.QUERY_DATA.getCode().equals(commandNum)) {
            DeviceRecordData data = JsonUtils.json2Object(message, DeviceRecordData.class);
            DeviceRecordDto deviceRecordDto = data.getData();
            LOGGER.info("HandlerMessageService.handlerMessage deviceRecordDto={} ", JsonUtils.object2Json(deviceRecordDto));
            deviceRecordService.insertOrUpdate(deviceRecordDto);
        } else if (CommandEnum.QUERY_STORE_INFO.getCode().equals(commandNum)) {
            BaseData<StoreInfoDto> base = JsonUtils.json2Object(message, BaseData.class);
            StoreInfoDto StoreInfoDto = base.getData();
        }

        return null;

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void sendMessage(ChannelHandlerContext channelHandlerContext) {
        String address = channelHandlerContext.channel().remoteAddress().toString();
        String deviceNum = ServerHandler.addressToDeviceNumMap.get(address);
        DeviceInfoEntity deviceInfo = deviceInfoDao.getDeviceByDeviceNum(deviceNum);
        if (StringUtils.isBlank(deviceNum) || deviceInfo == null) {
            sendDelayMessageService.send(channelHandlerContext, CommandEnum.QUERY_PARAM.getCommond());
            LOGGER.warn(channelHandlerContext.channel().remoteAddress() + " 发送 : " + CommandEnum.QUERY_PARAM.getCommond());
            return;
        }

        //获取后台配置信息
        DeviceSettingDto settingDto = settingService.getSettingDto(deviceInfo.getId());
        if (settingDto == null) {
            sendDelayMessageService.send(channelHandlerContext, CommandEnum.QUERY_PARAM.getCommond());
            LOGGER.warn(channelHandlerContext.channel().remoteAddress() + "没有查询到配置信息 发送 : " + CommandEnum.QUERY_PARAM.getCommond());
            return;
        }
        Integer oknum = ServerHandler.clientOKNum.get(channelHandlerContext.channel().remoteAddress().toString());
        oknum = oknum != null ? oknum % 9 : 0;

        //第一个ok 查询采集信息
        if (oknum == 0) {
            String dateformat = DateUtils.getDateformat(new Date(), DateUtils.YMD_FORMAT1);
            sendDelayMessageService.send(channelHandlerContext, CommandEnum.QUERY_NEW_DATA1.getCommond() + dateformat);
        }
        if (oknum == 1) {
            //测试30分钟启动一次客户端
            if (config.getEnv().equals("test")) {
                Date afterMinutes = DateUtils.getDateAfterMinutes(new Date(), 30);
                String hhmm = DateUtils.getDateformat(afterMinutes, DateUtils.HHmm);
                settingDto.setUploadTime(hhmm);
            }
            sendDelayMessageService.send(channelHandlerContext, CommandEnum.getClientSettingParam(settingDto));
        }

        LOGGER.info("ServerHandler.channelRead0 address={}  oknum={}", address, oknum);
        //请求ok次数
        ServerHandler.clientOKNum.put(channelHandlerContext.channel().remoteAddress().toString(), ++oknum);

    }

    private DeviceInfoDto parseToDeviceInfoDto(DeviceSettingDto deviceSettingDto) {
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDto.setDeviceNum(deviceSettingDto.getDeviceNum());
        deviceInfoDto.setDeviceType(DeviceTypeEnum.WELL.getCode());
        deviceInfoDto.setSerialNum(deviceSettingDto.getWellNum());
        return deviceInfoDto;

    }

}
