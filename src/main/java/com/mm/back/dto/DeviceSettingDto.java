package com.mm.back.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:08
 * Desc:设备配置信息
 */
public class DeviceSettingDto {


    private Integer deviceId;
    private Integer wellNum;
    private String deviceNum;
    private String currentTime;
    private BigDecimal sensorDepth;
    private BigDecimal surfaceHigh;

    private String phonenumber1;
    private String phonenumber2;
    private String IPAddress;
    private Integer portNumber;
    private String wakeupTime1;
    private String wakeupTime2;
    private BigDecimal linearCoefficient;
    private String softwareVersion;

    public Integer getWellNum() {
        return wellNum;
    }

    public void setWellNum(Integer wellNum) {
        this.wellNum = wellNum;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public BigDecimal getSensorDepth() {
        return sensorDepth;
    }

    public void setSensorDepth(BigDecimal sensorDepth) {
        this.sensorDepth = sensorDepth;
    }

    public BigDecimal getSurfaceHigh() {
        return surfaceHigh;
    }

    public void setSurfaceHigh(BigDecimal surfaceHigh) {
        this.surfaceHigh = surfaceHigh;
    }

    public BigDecimal getLinearCoefficient() {
        return linearCoefficient;
    }

    public void setLinearCoefficient(BigDecimal linearCoefficient) {
        this.linearCoefficient = linearCoefficient;
    }

    public String getPhonenumber1() {
        return phonenumber1;
    }

    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }

    public String getPhonenumber2() {
        return phonenumber2;
    }

    public void setPhonenumber2(String phonenumber2) {
        this.phonenumber2 = phonenumber2;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public String getWakeupTime1() {
        return wakeupTime1;
    }

    public void setWakeupTime1(String wakeupTime1) {
        this.wakeupTime1 = wakeupTime1;
    }

    public String getWakeupTime2() {
        return wakeupTime2;
    }

    public void setWakeupTime2(String wakeupTime2) {
        this.wakeupTime2 = wakeupTime2;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }


}
