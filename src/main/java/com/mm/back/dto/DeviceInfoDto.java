package com.mm.back.dto;



/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:08
 * Desc:描述该类的作用
 */
public class DeviceInfoDto {


    private Integer deviceId;
    /**
     * 如井号
     */
    private Integer serialNum;
    /**
     * 设备硬件编号
     */
    private String deviceNum;
    /**
     * 设备类型
     *
     * @see com.mm.back.constants.DeviceTypeEnum
     */
    private Integer deviceType;


    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

}
