package com.mm.back.common;

import java.util.Date;

public class Role {

	private Integer id;
	/**
	 * 角色名
	 * 
	 */
	private String roleName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 删除标记
	 */
	private Integer isDel;
	
	
	/**
	 * 是否有权限 0 没有 1 有
	 */
	private Integer isAuthority;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getIsAuthority() {
		return isAuthority;
	}


	public void setIsAuthority(Integer isAuthority) {
		this.isAuthority = isAuthority;
	}
	
	
	
}
