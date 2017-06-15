package com.mm.back.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author:chyl2005
 * Date:17/4/16
 * Time:09:06
 * 设备上传记录信息
 */
public class UploadRecordDto {

    /**
     * 设备编号
     */
    private Integer deviceNum;

    /**
     * 序列号
     */
    private Integer serialNum;
    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 上传时间
     */
    private Date uploadTime;

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

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }
}
