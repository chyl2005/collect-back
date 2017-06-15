package com.mm.back.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单
 */
public class Menu implements Serializable{

	
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
    /**
     * 请求方法路径 唯一
     */
	private String url;
	/**
	 * 模块编号 
	 */
	private Integer orderNum;
	/**
	 * 模块父类id
	 */
	private Integer parentId;
	
	
	/**
	 * 级别
	 */
	private Integer level;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态 1 正常 0 删除
	 */
	private Integer state;
	private Integer type;
	
	private List<Menu>  subModule;
	
	
	/**
	 * 是否有权限 0 没有 1 有
	 */
	private Integer isAuthority;
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Menu> getSubModule() {
		return subModule;
	}
	public void setSubModule(List<Menu> subModule) {
		this.subModule = subModule;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsAuthority() {
		return isAuthority;
	}
	public void setIsAuthority(Integer isAuthority) {
		this.isAuthority = isAuthority;
	}
	
	
}
