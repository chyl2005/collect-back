package com.mm.back.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:08
 * Desc:设备配置信息
 */
@JsonAutoDetect
public class DeviceSettingDto {

    private Integer deviceId;
    private Integer wellNum;
    private String deviceNum;
    private String currentTime;
    private BigDecimal sensorDepth;
    private BigDecimal surfaceHigh;

    private String phonenumber1;
    private String phonenumber2;
    @JsonProperty("IPAddress")
    private String IPAddress;
    private Integer portNumber;

    private BigDecimal linearCoefficient;
    private String softwareVersion;

    /**
     * 12/11  mm:ss
     */
    @JsonProperty("UploadTime")
    private String uploadTime;
    /**
     * 四位   不足补零  时间间隔，每天测量多次，每次的时间间隔，单位是分钟，最大1440
     */
    @JsonProperty("WakeInterval")
    private String wakeInterval;

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

    @JsonProperty("IPAddress")
    public String getIPAddress() {
        return IPAddress;
    }

    @JsonProperty("IPAddress")
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    @JsonProperty("UploadTime")
    public String getUploadTime() {
        return uploadTime;
    }

    @JsonProperty("UploadTime")
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getWakeInterval() {
        return wakeInterval;
    }

    public void setWakeInterval(String wakeInterval) {
        this.wakeInterval = wakeInterval;
    }
}
