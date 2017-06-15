package com.mm.back.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 系统用户表
 */
@Entity
@Table(name="tb_user")
public class UserEntity implements Serializable{
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	/**
	 * 账号名
	 */
	@Column(name = "user_name")
    private String userName;
	@Column(name = "password")
    private String password;
	/**
	 *真实姓名
	 */
	@Column(name = "true_name")
    private String trueName;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}




 
}
