package com.mm.back.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:11:30
 * Desc:设备基础信息
 */
@Entity
@Table(name = "device_info ")
public class DeviceInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 如井号
     */
    @Column(name = "serial_num")
    private Integer serialNum;
    /**
     * 设备硬件编号
     */
    @Column(name = "device_num")
    private Integer deviceNum;
    /**
     * 设备类型
     *
     * @see com.mm.back.constants.DeviceTypeEnum
     */
    @Column(name = "device_type")
    private Integer deviceType;
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "city_name")
    private Integer cityName;
    @Column(name = "province_id")
    private Integer provinceId;
    @Column(name = "province_name")
    private Integer provinceName;
    /**
     * 创建时间
     */
    @Column(name="gmt_created")
    private Date gmtCreated;
    /**
     * 修改时间
     */
    @Column(name="gmt_modified")
    private Date gmtModified;

    /**
     * @see com.mm.back.constants.DeleteStatusEnum
     */
    @Column(name = "is_del")
    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityName() {
        return cityName;
    }

    public void setCityName(Integer cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(Integer provinceName) {
        this.provinceName = provinceName;
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
