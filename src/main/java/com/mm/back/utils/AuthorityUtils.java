

package com.mm.back.utils;



import java.util.*;
import com.mm.back.entity.ModuleEntity;
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
	public static List<Menu> getModuleVos(List<ModuleEntity> moduleEntities) {
		// 一级菜单
		List<Menu> firstList = new ArrayList<Menu>();
		// 二级菜单
		List<Menu> secondList = new ArrayList<Menu>();
		// 三级菜单
		List<Menu> thirdList = new ArrayList<Menu>();
		for (ModuleEntity moduleEntity : moduleEntities) {
			if (moduleEntity.getLevel() == 1) {
				firstList.add(parseToVo(moduleEntity));
			}
			if (moduleEntity.getLevel() == 2) {
				secondList.add(parseToVo(moduleEntity));
			}
			if (moduleEntity.getLevel() == 3) {
				thirdList.add(parseToVo(moduleEntity));
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
				List<Menu> subModule = secondMenu.getSubModule();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(menu);
				secondMenu.setSubModule(subModule);
			}
		}
		// 二级组装到第一级
		for (Map.Entry<Integer, Menu> entry : secondModuleMap.entrySet()) {
			Menu moduleVo = entry.getValue();
			// 二级列表有权限
			Menu firstMenu = firstModuleMap.get(moduleVo.getParentId());
			if (firstMenu != null) {
				List<Menu> subModule = firstMenu.getSubModule();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(moduleVo);
				firstMenu.setSubModule(subModule);

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
	public static List<Menu> getModuleVos(List<ModuleEntity> moduleEntities, Set<Integer> moduleIds) {
		// 一级菜单
		List<Menu> firstList = new ArrayList<Menu>();
		// 二级菜单
		List<Menu> secondList = new ArrayList<Menu>();
		// 三级菜单
		List<Menu> thirdList = new ArrayList<Menu>();
		for (ModuleEntity moduleEntity : moduleEntities) {
			Menu menu = parseToVo(moduleEntity);
			if (moduleIds != null && moduleIds.size() > 0 && moduleIds.contains(menu.getId())) {
				menu.setIsAuthority(1);
			} else {
				menu.setIsAuthority(0);
			}
			if (moduleEntity.getLevel() == 1) {
				firstList.add(menu);
			}
			if (moduleEntity.getLevel() == 2) {
				secondList.add(menu);
			}
			if (moduleEntity.getLevel() == 3) {
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
				List<Menu> subModule = secondMenu.getSubModule();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(menu);
				secondMenu.setSubModule(subModule);
			}
		}
		// 二级组装到第一级
		for (Map.Entry<Integer, Menu> entry : secondModuleMap.entrySet()) {
			Menu menu = entry.getValue();
			// 二级列表有权限
			Menu firstMenu = firstModuleMap.get(menu.getParentId());
			if (firstMenu != null) {
				List<Menu> subModule = firstMenu.getSubModule();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(menu);
				firstMenu.setSubModule(subModule);

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
	public static Menu parseToVo(ModuleEntity entity) {
		Menu menu = new Menu();
		menu.setId(entity.getId());
		menu.setName(entity.getName());
		menu.setOrderNum(entity.getOrderNum());
		menu.setParentId(entity.getParentId());
		menu.setUrl(entity.getUrl());
		menu.setLevel(entity.getLevel());
		menu.setCreateTime(entity.getGmtCreated());
		menu.setState(entity.getIsDel());
		menu.setSubModule(new ArrayList<Menu>());
		return menu;
	}
}
