package com.mm.back.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:11:30
 * Desc:设备上传记录信息
 */
@Entity
@Table(name = "device_record ", uniqueConstraints = {@UniqueConstraint(columnNames = {"device_id", "datekey"})})
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
     * 20170101
     */
    @Column(name = "datekey")
    private Integer datekey;
    /**
     *
     *
     */
    @Column(name = "air_temperature")
    private BigDecimal airTemperature;

    @Column(name = "water_temperature")
    private BigDecimal waterTemperature;
    @Column(name = "water_high")
    private BigDecimal waterHigh;
    @Column(name = "water_depth")
    private BigDecimal waterDepth;

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
}
