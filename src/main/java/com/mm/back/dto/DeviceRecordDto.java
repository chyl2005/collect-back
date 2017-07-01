package com.mm.back.dto;

import java.math.BigDecimal;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:13:15
 * Desc:描述该类的作用
 */
public class DeviceRecordDto {

    /**
     * 设备ID
     */
    private Integer deviceId;
    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 井号
     */
    private Integer wellNum;
    /**
     * 时间格式   2017/4/21/9/45/55
     */
    private String collectTime;

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

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public Integer getWellNum() {
        return wellNum;
    }

    public void setWellNum(Integer wellNum) {
        this.wellNum = wellNum;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
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

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
