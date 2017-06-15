package com.mm.back.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:08
 * Desc:设备配置信息
 */
public class DeviceConfigResponse {

    private Integer deviceId;
    private Integer serverIp;
    private Integer serverPort;
    private String phoneNum1;

    private String phoneNum2;
    private BigDecimal sensorDepth;
    private BigDecimal surfaceHigh;
    private String wakeupTime1;
    private String wakeupTime2;

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

    public Integer getServerIp() {
        return serverIp;
    }

    public void setServerIp(Integer serverIp) {
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
}
