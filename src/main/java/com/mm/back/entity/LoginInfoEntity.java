package com.mm.back.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 系统用户表
 */
@Entity
@Table(name="tb_login_info", uniqueConstraints = {@UniqueConstraint(columnNames="login_key")})
@DynamicInsert
@DynamicUpdate
public class LoginInfoEntity implements Serializable{
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	/**
	 * 登录key ssoid
	 */
	@Column(name = "login_key")
    private String loginKey;

	/**
	 * 登录信息 权限
	 */
	@Column(name = "login_info")
    private String loginInfo;


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


	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	public String getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(String loginInfo) {
		this.loginInfo = loginInfo;
	}
}
