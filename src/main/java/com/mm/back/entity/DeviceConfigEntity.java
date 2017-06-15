package com.mm.back.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:11:30
 * Desc:设备参数信息
 */
@Entity
@Table(name = "device_config ", uniqueConstraints = {@UniqueConstraint(columnNames = {"device_id"})})
public class DeviceConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * DeviceInfoEntity id
     */
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "server_ip")
    private Integer serverIp;
    @Column(name = "server_port")
    private Integer serverPort;
    @Column(name = "phone_num1")
    private String phoneNum1;

    @Column(name = "phone_num2")
    private String phoneNum2;
    @Column(name = "sensor_depth")
    private BigDecimal sensorDepth;
    @Column(name = "surface_high")
    private BigDecimal surfaceHigh;
    @Column(name = "wakeup_time1")
    private String wakeupTime1;
    @Column(name = "wakeup_time2")
    private String wakeupTime2;

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
     * 删除标记
     */
    @Column(name="is_del")
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
}
