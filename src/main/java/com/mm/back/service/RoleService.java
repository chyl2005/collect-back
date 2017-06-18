package com.mm.back.service;



import java.util.*;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.mm.back.common.AoData;
import com.mm.back.common.Menu;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.AuthorityDao;
import com.mm.back.dao.ModuleDao;
import com.mm.back.dao.RoleDao;
import com.mm.back.entity.AuthorityEntity;
import com.mm.back.entity.MenuEntity;
import com.mm.back.entity.RoleEntity;
import com.mm.back.utils.AuthorityUtils;

@Service("roleService")
public class RoleService {

	@Resource(name = "roleDao")
	private RoleDao roleDao;

	@Resource(name = "authorityDao")
	private AuthorityDao authorityDao;

	@Resource(name = "moduleDao")
	private ModuleDao moduleDao;

	/**
	 * 
	 * @Description:
	 *            是否删除的状态
	 * @return List<RoleEntity> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月24日 下午1:13:44
	 */
	public AoData getAllRoles() {
		return this.roleDao.getAllRoles();
	}

	/**
	 * 
	 * @Description:
	 * @param entity
	 * @return RoleEntity 返回类型
	 * @author chenyanlong
	 * @date 2015年11月24日 下午1:19:24
	 */
	public RoleEntity updateEntity(RoleEntity entity) {

		return this.roleDao.updateEntity(entity);
	}

	/**
	 * 
	 * @Description:
	 * @param roleId
	 * @param isDel
	 * @return Boolean 返回类型
	 * @author chenyanlong
	 * @date 2015年11月24日 下午1:20:07
	 */
	public Boolean del(Integer roleId, Integer isDel) {
		Boolean updateState = this.roleDao.del(roleId, isDel);
		return updateState;
	}

	/**
	 * 
	 * @Description:
	 * @param entity
	 * @return RoleEntity 返回类型
	 * @author chenyanlong
	 * @date 2015年11月24日 下午1:21:12
	 */
	public RoleEntity saveEntity(RoleEntity entity) {
		return this.roleDao.saveEntity(entity);

	}

	/**
	 * 
	 * @Description: 获取所有模块的角色权限信息
	 * @param roleId
	 * @return List<ModuleVo> 返回类型
	 * @author chenyanlong
	 * @date 2016年3月17日 上午9:24:26
	 */
	public List<Menu> getAllModuleAuthority(Integer roleId) {
		// 当前角色所拥有的权限
		Set<Integer> moduleIds = new HashSet<Integer>();
		if (null!=roleId) {
			AuthorityEntity authorityEntity = this.authorityDao.getAuthority(roleId);
			if (authorityEntity!=null&&StringUtils.isNotBlank( authorityEntity.getModuleId())) {
				String[] ids =  authorityEntity.getModuleId().split(",");
				for (String id : ids) {
					moduleIds.add(Integer.parseInt(id));
				}
			}
		}
		// 取出所有模块 转成树形结构
		List<MenuEntity> modules = this.moduleDao.getAllModules();
		List<Menu> moduleVos = null;
		if (null != modules && modules.size() > 0) {
			moduleVos = AuthorityUtils.getModuleVos(modules, moduleIds);
		}
		return moduleVos;
	}

	/**
	 * 分配模块
	 * 
	 * @param
	 * @return
	 */
	public boolean distributeModule(int roleId, String moduleIds) {
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setRoleId(roleId);
		authorityEntity.setModuleId(moduleIds);
		authorityDao.saveOrUpdateEntity(authorityEntity);
		return true;
	}


	private Map<Integer, Menu> convertListToMap(List<MenuEntity> moduleEntities) {
		// 一级菜单
		List<Menu> firstList = new ArrayList<Menu>();
		// 二级菜单
		List<Menu> secondList = new ArrayList<Menu>();
		// 三级菜单
		List<Menu> thirdList = new ArrayList<Menu>();
		for (MenuEntity menuEntity : moduleEntities) {
			if (menuEntity.getLevel() == 1) {
				firstList.add(AuthorityUtils.parseToVo(menuEntity));
			}
			if (menuEntity.getLevel() == 2) {
				secondList.add(AuthorityUtils.parseToVo(menuEntity));
			}
			if (menuEntity.getLevel() == 3) {
				thirdList.add(AuthorityUtils.parseToVo(menuEntity));
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

		HashMap<Integer, Menu> thirdModuleMap = new HashMap<Integer, Menu>();
		// 三级组装到第二级
		for (Menu moduleVo : thirdList) {
			thirdModuleMap.put(moduleVo.getId(), moduleVo);
			// 二级列表有权限
			Menu secondModuleVo = secondModuleMap.get(moduleVo.getParentId());
			if (secondModuleVo != null) {
				List<Menu> subModule = secondModuleVo.getSubModule();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(moduleVo);
				secondModuleVo.setSubModule(subModule);
			}
		}
		// 二级组装到第一级
		for (Map.Entry<Integer, Menu> entry : secondModuleMap.entrySet()) {
			Menu moduleVo = entry.getValue();
			// 二级列表有权限
			Menu firstModuleVo = firstModuleMap.get(moduleVo.getParentId());
			if (firstModuleVo != null) {
				List<Menu> subModule = firstModuleVo.getSubModule();
				if (null == subModule) {
					subModule = new ArrayList<Menu>();
				}
				subModule.add(moduleVo);
				firstModuleVo.setSubModule(subModule);

			}
		}
		firstModuleMap.putAll(secondModuleMap);
		firstModuleMap.putAll(thirdModuleMap);
		return firstModuleMap;
	}

	public RoleEntity saveOrUpdate(RoleEntity entity) {
		if (entity.getId()==null) {//添加
			entity.setGmtCreated(new Date());
			entity.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
			entity.setGmtModified(new Date());
			this.roleDao.saveEntity(entity);
			return entity;
		}else {//修改
			RoleEntity roleEntity = this.roleDao.getRoleEntity(entity.getId());
			roleEntity.setRoleName(entity.getRoleName());
			entity.setGmtModified(new Date());
			this.roleDao.updateEntity(roleEntity);
			return roleEntity;
		}
	}

	public void del(Integer id) {
		RoleEntity roleEntity = this.roleDao.getRoleEntity(id);
		if (null!=roleEntity) {
			this.roleDao.del(roleEntity.getId(), DeleteStatusEnum.DEL.getCode());
		}
	}

}
