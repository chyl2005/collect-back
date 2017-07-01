package com.mm.back.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:11:30
 * Desc:设备上传记录信息
 */
@Entity
@Table(name = "device_record")
@DynamicInsert
@DynamicUpdate
public class DeviceRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     *
     */

    @Column(name = "device_id")
    private Integer deviceId;

    /**
     * 设备编号
     */
    @Column(name = "device_num")
    private String deviceNum;
    /**
     * 20170101
     */
    @Column(name = "datekey")
    private Integer datekey;

    @Column(name = "sensor_depth")
    private BigDecimal sensorDepth;
    @Column(name = "surface_high")
    private BigDecimal surfaceHigh;

    @Column(name = "water_high")
    private BigDecimal waterHigh;
    @Column(name = "water_depth")
    private BigDecimal waterDepth;

    /**
     * 气温
     */
    @Column(name = "air_temperature")
    private BigDecimal airTemperature;

    /**
     * 水温
     */
    @Column(name = "water_temperature")
    private BigDecimal waterTemperature;
    /**
     * 电压
     */
    @Column(name = "voltage")
    private BigDecimal voltage;

    /**
     * 信号
     */
    @Column(name = "signals")
    private BigDecimal signal;

    /**
     * 采集时间
     */
    @Column(name = "collect_time")
    private Date collectTime;
    /**
     * 创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;
    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;
    /**
     * 删除标记
     */
    @Column(name = "is_del")
    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
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

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }
}
