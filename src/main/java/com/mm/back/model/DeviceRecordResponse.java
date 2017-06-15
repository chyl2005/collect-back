package com.mm.back.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/15
 * Time:13:15
 * Desc:描述该类的作用
 */
public class DeviceRecordResponse {

    /**
     * 设备ID
     */
    private Integer deviceId;
    /**
     * 20170101
     */
    private Integer datekey;
    /**
     * 气温
     */
    private BigDecimal airTemperature;
    /**
     * 水温
     */
    private BigDecimal waterTemperature;
    /**
     *
     */
    private BigDecimal waterHigh;
    private BigDecimal waterDepth;

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
}
