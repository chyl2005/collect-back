package com.mm.back.service;



import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.common.Menu;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.AuthorityDao;
import com.mm.back.dao.ModuleDao;
import com.mm.back.dao.RoleDao;
import com.mm.back.entity.AuthorityEntity;
import com.mm.back.entity.MenuEntity;
import com.mm.back.entity.RoleEntity;
import com.mm.common.utils.AuthorityUtils;

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
	@Transactional
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
	@Transactional
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
	@Transactional
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
		List<AuthorityEntity> authoritys = this.authorityDao.getAuthority(roleId);
		Set<Integer> moduleIds =authoritys.stream().map(authorityEntity -> authorityEntity.getMenuId()).distinct().collect(Collectors.toSet());
		// 取出所有模块 转成树形结构
		List<MenuEntity> modules = this.moduleDao.getAllModules();
		List<Menu> moduleVos = null;
		if (null != modules && modules.size() > 0) {
			moduleVos = AuthorityUtils.getModuleVos(modules, moduleIds);
		}
		return moduleVos;
	}



	@Transactional
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
			roleEntity.setGmtModified(new Date());
			this.roleDao.updateEntity(roleEntity);
			return roleEntity;
		}
	}
	@Transactional
	public void del(Integer id) {
		RoleEntity roleEntity = this.roleDao.getRoleEntity(id);
		if (null!=roleEntity) {
			this.roleDao.del(roleEntity.getId(), DeleteStatusEnum.DEL.getCode());
		}
	}

}
