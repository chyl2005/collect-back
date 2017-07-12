

package com.mm.common.utils;



import java.util.*;
import com.mm.back.entity.MenuEntity;
import com.mm.back.common.Menu;

/**
 * @ClassName: AuthorityUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenyanlong
 * @date 2015年11月26日
 *
 */
public class AuthorityUtils {
	/**
	 * @Description: 模块列表转成树形结构
	 * @param moduleEntities
	 * @return List<ModuleVo> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月26日 上午9:48:07
	 */
	public static List<Menu> getModuleVos(List<MenuEntity> moduleEntities) {
		// 一级菜单
		List<Menu> firstList = new ArrayList<Menu>();
		// 二级菜单
		List<Menu> secondList = new ArrayList<Menu>();
		// 三级菜单
		List<Menu> thirdList = new ArrayList<Menu>();
		for (MenuEntity menuEntity : moduleEntities) {
			if (menuEntity.getLevel() == 1) {
				firstList.add(parseToVo(menuEntity));
			}
			if (menuEntity.getLevel() == 2) {
				secondList.add(parseToVo(menuEntity));
			}
			if (menuEntity.getLevel() == 3) {
				thirdList.add(parseToVo(menuEntity));
			}
		}
		// 转成map
		HashMap<Integer, Menu> firstModuleMap = new HashMap<Integer, Menu>();
		for (Menu module : firstList) {
			firstModuleMap.put(module.getId(), module);
		}
		// 转成map
		HashMap<Integer, Menu> secondModuleMap = new HashMap<Integer, Menu>();
		for (Menu module : secondList) {
			secondModuleMap.put(module.getId(), module);
		}
		// 三级组装到第二级
		for (Menu menu : thirdList) {
			// 二级列表有权限
			Menu secondMenu = secondModuleMap.get(menu.getParentId());
			if (secondMenu != null) {
				List<Menu> subModule = secondMenu.getSubMenus();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(menu);
				secondMenu.setSubMenus(subModule);
			}
		}
		// 二级组装到第一级
		for (Map.Entry<Integer, Menu> entry : secondModuleMap.entrySet()) {
			Menu moduleVo = entry.getValue();
			// 二级列表有权限
			Menu firstMenu = firstModuleMap.get(moduleVo.getParentId());
			if (firstMenu != null) {
				List<Menu> subModule = firstMenu.getSubMenus();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(moduleVo);
				firstMenu.setSubMenus(subModule);

			}
		}
		// 重新赋值
		for (Menu menu : firstList) {
			menu = firstModuleMap.get(menu.getId());
		}
		return firstList;
	}

	/**
	 * @Description: 模块列表转成树形结构
	 * @param moduleEntities
	 * @return List<ModuleVo> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月26日 上午9:48:07
	 */
	public static List<Menu> getModuleVos(List<MenuEntity> moduleEntities, Set<Integer> moduleIds) {
		// 一级菜单
		List<Menu> firstList = new ArrayList<Menu>();
		// 二级菜单
		List<Menu> secondList = new ArrayList<Menu>();
		// 三级菜单
		List<Menu> thirdList = new ArrayList<Menu>();
		for (MenuEntity menuEntity : moduleEntities) {
			Menu menu = parseToVo(menuEntity);
			if (moduleIds != null && moduleIds.size() > 0 && moduleIds.contains(menu.getId())) {
				menu.setIsAuthority(1);
			} else {
				menu.setIsAuthority(0);
			}
			if (menuEntity.getLevel() == 1) {
				firstList.add(menu);
			}
			if (menuEntity.getLevel() == 2) {
				secondList.add(menu);
			}
			if (menuEntity.getLevel() == 3) {
				thirdList.add(menu);
			}
		}
		// 转成map
		HashMap<Integer, Menu> firstModuleMap = new HashMap<Integer, Menu>();
		for (Menu module : firstList) {
			firstModuleMap.put(module.getId(), module);
		}
		// 转成map
		HashMap<Integer, Menu> secondModuleMap = new HashMap<Integer, Menu>();
		for (Menu module : secondList) {
			secondModuleMap.put(module.getId(), module);
		}
		// 三级组装到第二级
		for (Menu menu : thirdList) {
			// 二级列表有权限
			Menu secondMenu = secondModuleMap.get(menu.getParentId());
			if (secondMenu != null) {
				List<Menu> subModule = secondMenu.getSubMenus();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(menu);
				secondMenu.setSubMenus(subModule);
			}
		}
		// 二级组装到第一级
		for (Map.Entry<Integer, Menu> entry : secondModuleMap.entrySet()) {
			Menu menu = entry.getValue();
			// 二级列表有权限
			Menu firstMenu = firstModuleMap.get(menu.getParentId());
			if (firstMenu != null) {
				List<Menu> subModule = firstMenu.getSubMenus();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(menu);
				firstMenu.setSubMenus(subModule);

			}
		}
		// 重新赋值
		for (Menu menu : firstList) {
			menu = firstModuleMap.get(menu.getId());
		}
		return firstList;
	}

	/**
	 * 
	 * @Description:
	 * @param entity
	 * @return ModuleVo 返回类型
	 * @author chenyanlong
	 * @date 2015年11月25日 下午2:04:23
	 */
	public static Menu parseToVo(MenuEntity entity) {
		Menu menu = new Menu();
		menu.setId(entity.getId());
		menu.setName(entity.getName());
		menu.setParentId(entity.getParentId());
		menu.setUrl(entity.getUrl());
		menu.setLevel(entity.getLevel());
		menu.setCreateTime(entity.getGmtCreated());
		menu.setState(entity.getIsDel());
		menu.setSubMenus(new ArrayList<Menu>());
		menu.setIsLink(entity.getIsLink());
		menu.setIcon(entity.getIcon());
		return menu;
	}
}
