package com.mm.back.service;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.User;
import com.mm.back.dao.UserDao;
import com.mm.back.dao.impl.AuthorityDaoImpl;
import com.mm.back.dao.impl.ModuleDaoImpl;
import com.mm.back.dao.impl.UserRoleDaoImpl;
import com.mm.back.entity.AuthorityEntity;
import com.mm.back.entity.ModuleEntity;
import com.mm.back.entity.UserEntity;
import com.mm.back.entity.UserRoleEntity;
import com.mm.back.utils.AuthorityUtils;
import com.mm.back.common.Menu;
import com.mm.back.vo.UserInfoCacheVo;

/**
 * 
 * @ClassName: AuthorityService
 * @Description: 权限
 * @author chenyanlong
 * @date 2015年11月23日 下午3:53:42
 */
@Service("authorityService")
public class AuthorityService {
	private Logger log = LogManager.getLogger(AuthorityService.class);
	@Resource(name = "authorityDao")
	private AuthorityDaoImpl authorityDaoImpl;
	@Resource(name = "userRoleDao")
	private UserRoleDaoImpl userRoleDaoImpl;
	@Resource(name = "moduleDao")
	private ModuleDaoImpl moduleDaoImpl;
	@Autowired
	private UserDao userDao;

	/**
	 * @Description: 查询登录用户拥有权限的模块列表 子集授权 父级必须授权
	 * @return List<ModuleEntity> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午3:55:08
	 */
	public List<Menu> findModulesByUser(Integer userId) {
		Set<Integer> moduleIds = new HashSet<Integer>();
		List<UserRoleEntity> urs = userRoleDaoImpl.getUserRole(userId);
		// 用户所拥有的角色权限 模块列表
		String moduleId = "";
		for (UserRoleEntity userRoleEntity : urs) {
			List<AuthorityEntity> authoritys = this.authorityDaoImpl.getAuthoritys(userRoleEntity.getRoleId());
			for (AuthorityEntity authorityEntity : authoritys) {
				moduleId += authorityEntity.getModuleId() + ",";
			}
		}
		String[] ids = {};
		if (StringUtils.isNotBlank(moduleId)) {
			ids = moduleId.split(",");
		}
		// 取出拥有读取权限的所有模块
		if (ids.length > 0) {
			for (String s : ids) {
				moduleIds.add(Integer.parseInt(s));
			}
			// 用户拥有权限的所有模块
			List<ModuleEntity> moduleEntities = this.moduleDaoImpl.getModules(moduleIds);
			List<Menu> menus = null;
			if (null != moduleEntities && moduleEntities.size() > 0) {
				menus = AuthorityUtils.getModuleVos(moduleEntities);
				return menus;
			}
		}
		return new ArrayList<Menu>();
	}

	/**
	 * @Description: 查询登录用户拥有权限的模块列表 子集授权 父级必须授权
	 * @return UserInfoCacheVo 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午3:55:08
	 */
	public UserInfoCacheVo getUserInfoCache(Integer userId) {
		UserInfoCacheVo userInfoCache = new UserInfoCacheVo();
        UserEntity userInfo = userDao.getUserInfo(userId);
        User user = new User();
        user.setId(userInfo.getId());
        user.setLogin(userInfo.getUserName());
        user.setName(userInfo.getTrueName());
        userInfoCache.setUser(user);
        Set<Integer> moduleIds = new HashSet<Integer>();
		List<UserRoleEntity> userRoleEntities = userRoleDaoImpl.getUserRole(userId);
		// 用户所拥有的角色列表
		Set<Integer> roleIds = new HashSet<Integer>();
		for (UserRoleEntity userRoleEntity : userRoleEntities) {
			roleIds.add(userRoleEntity.getRoleId());
		}
		if (roleIds.size()==0) {
			return null;
		}
		//权限信息
		List<AuthorityEntity> authoritys = this.authorityDaoImpl.getAuthoritys(roleIds);
		for (AuthorityEntity authorityEntity : authoritys) {
		       if ( StringUtils.isNotBlank(authorityEntity.getModuleId())) {
		    	   String[] ids = authorityEntity.getModuleId().split(",");
		    	   for (String id : ids) {
		    		   moduleIds.add(Integer.parseInt(id));
				}
			}
		}
		// 取出拥有读取权限的所有模块
		if (moduleIds.size() > 0) {
			// 用户拥有权限的所有模块
			List<ModuleEntity> moduleEntities = this.moduleDaoImpl.getModules(moduleIds);
			List<Menu> menus = null;
			if (null != moduleEntities && moduleEntities.size() > 0) {
				menus = AuthorityUtils.getModuleVos(moduleEntities);
				userInfoCache.setMenus(menus);
			}
		}
		userInfoCache.setMenuIds(moduleIds);
		return userInfoCache;
	}

	/**
	 * 
	 * @Description: 查询所有可用模块
	 * @param state
	 * @return List<ModuleEntity> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月26日 上午9:45:13
	 */
	//以非事务方式执行
	public List<Menu> getAllModules(Integer state) {
		List<ModuleEntity> modules = this.moduleDaoImpl.getAllModules(state);
		List<Menu> menus = null;
		if (null != modules && modules.size() > 0) {
			menus = AuthorityUtils.getModuleVos(modules);
			return menus;
		}
		return new ArrayList<Menu>();
	}

	/**
	 * 
	* @Description: 
	* @param  authorityEntity
	* @return AuthorityEntity    返回类型 
	* @author chenyanlong
	* @date 2016年3月16日 下午5:42:38
	 */
	@Transactional()
	public AuthorityEntity saveOrUpdateEntity(AuthorityEntity authorityEntity) {
		return this.authorityDaoImpl.saveOrUpdateEntity(authorityEntity);
	}
}
