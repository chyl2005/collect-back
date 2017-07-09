package com.mm.back.vo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:08
 * Desc:设备配置信息
 */
public class DeviceSettingVo {

    private Integer deviceId;
    /**
     * 井号
     */
    private Integer serialNum;
    private String deviceNum;
    private String phoneNum1;
    private String phoneNum2;
    private BigDecimal sensorDepth;
    private BigDecimal surfaceHigh;
    private String serverIp;
    private Integer serverPort;
    /**
     * 12/11  mm:ss
     */
    private String uploadTime;
    /**
     * 四位   不足补零  时间间隔，每天测量多次，每次的时间间隔，单位是分钟，最大1440
     */
    private String wakeInterval;

    private BigDecimal linearCoefficient;
    private String softwareVersion;


    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * @see com.mm.back.constants.DeleteStatusEnum
     */

    private Integer isDel;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getPhoneNum1() {
        return phoneNum1;
    }

    public void setPhoneNum1(String phoneNum1) {
        this.phoneNum1 = phoneNum1;
    }

    public String getPhoneNum2() {
        return phoneNum2;
    }

    public void setPhoneNum2(String phoneNum2) {
        this.phoneNum2 = phoneNum2;
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getWakeInterval() {
        return wakeInterval;
    }

    public void setWakeInterval(String wakeInterval) {
        this.wakeInterval = wakeInterval;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public BigDecimal getLinearCoefficient() {
        return linearCoefficient;
    }

    public void setLinearCoefficient(BigDecimal linearCoefficient) {
        this.linearCoefficient = linearCoefficient;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
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
}
