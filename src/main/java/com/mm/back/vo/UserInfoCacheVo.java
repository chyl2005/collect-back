package com.mm.back.vo;


import java.io.Serializable;
import java.util.List;
import java.util.Set;
import com.mm.back.common.Menu;
import com.mm.back.common.User;

public class UserInfoCacheVo implements Serializable{

	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private List<Menu> menus;
	private Set<Integer> menuIds;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Set<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Set<Integer> menuIds) {
		this.menuIds = menuIds;
	}
}
