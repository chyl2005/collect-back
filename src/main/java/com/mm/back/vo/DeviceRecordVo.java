package com.mm.back.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:15
 * Desc:描述该类的作用
 */
public class DeviceRecordVo {

    /**
     * 设备ID
     */
    private Integer deviceId;
    private String deviceNum;
    /**
     * 20170101
     */
    private Integer datekey;
    /**
     * 地表高程
     */
    private BigDecimal surfaceHigh;
    /**
     * 传感器深度
     */
    private BigDecimal sensorDepth;

    /**
     * 水面高程
     */
    private BigDecimal waterHigh;
    /**
     * 水面埋深
     */
    private BigDecimal waterDepth;
    /**
     * 气温
     */
    private BigDecimal airTemperature;

    /**
     * 水温
     */
    private BigDecimal waterTemperature;

    /**
     * 电压
     */
    private BigDecimal voltage;

    /**
     * 信号
     */
    private BigDecimal signal;

    private Date collectTime;
    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 删除标记
     */
    private Integer isDel;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDatekey() {
        return datekey;
    }

    public void setDatekey(Integer datekey) {
        this.datekey = datekey;
    }

    public BigDecimal getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(BigDecimal airTemperature) {
        this.airTemperature = airTemperature;
    }

    public BigDecimal getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(BigDecimal waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public BigDecimal getWaterHigh() {
        return waterHigh;
    }

    public void setWaterHigh(BigDecimal waterHigh) {
        this.waterHigh = waterHigh;
    }

    public BigDecimal getWaterDepth() {
        return waterDepth;
    }

    public void setWaterDepth(BigDecimal waterDepth) {
        this.waterDepth = waterDepth;
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

    public BigDecimal getSurfaceHigh() {
        return surfaceHigh;
    }

    public void setSurfaceHigh(BigDecimal surfaceHigh) {
        this.surfaceHigh = surfaceHigh;
    }

    public BigDecimal getSensorDepth() {
        return sensorDepth;
    }

    public void setSensorDepth(BigDecimal sensorDepth) {
        this.sensorDepth = sensorDepth;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getSignal() {
        return signal;
    }

    public void setSignal(BigDecimal signal) {
        this.signal = signal;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }
}
