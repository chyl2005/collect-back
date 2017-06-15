package com.mm.back.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/15
 * Time:13:08
 * Desc:描述该类的作用
 */
public class DeviceInfoResponse {


    private Integer deviceId;
    /**
     * 如井号
     */
    private Integer serialNum;
    /**
     * 设备硬件编号
     */
    private Integer deviceNum;
    /**
     * 设备类型
     *
     * @see com.mm.back.constants.DeviceTypeEnum
     */
    private Integer deviceType;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer cityId;
    private Integer cityName;
    private Integer provinceId;
    private Integer provinceName;
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
