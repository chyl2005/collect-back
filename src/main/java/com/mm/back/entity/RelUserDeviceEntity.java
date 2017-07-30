package com.mm.back.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 用户和设备关系表
 */
@Entity
@Table(name = "rel_user_device", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "device_id"})})
@DynamicInsert
@DynamicUpdate
public class RelUserDeviceEntity implements Serializable {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 账号名
     */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /**
     * 设备ID
     */
    @Column(name = "device_id", nullable = false)
    private Integer deviceId;

    /**
     * 创建时间
     */
    @Column(name = "gmt_created", nullable = false)
    private Date gmtCreated;
    /**
     * 修改时间
     */
    @Column(name = "gmt_modified", nullable = false)
    private Date gmtModified;
    /**
     * 删除标记
     */
    @Column(name = "is_del", nullable = false)
    private Integer isDel;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
