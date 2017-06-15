package com.mm.back.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 权限（访问控制）  
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_auth")
public class AuthorityEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 角色id
	 */
	@Column(name = "role_id")
	private Integer roleId;
	/**
	 * 模块ID
	 */
	@Column(name = "module_id")
	private String moduleId;
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


	

}
