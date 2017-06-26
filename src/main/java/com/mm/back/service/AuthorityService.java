package com.mm.back.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.Menu;
import com.mm.back.common.User;
import com.mm.back.controller.sys.LoginController;
import com.mm.back.dao.AuthorityDao;
import com.mm.back.dao.ModuleDao;
import com.mm.back.dao.UserDao;
import com.mm.back.dao.UserRoleDao;
import com.mm.back.entity.AuthorityEntity;
import com.mm.back.entity.MenuEntity;
import com.mm.back.entity.UserEntity;
import com.mm.back.entity.UserRoleEntity;
import com.mm.back.utils.AuthorityUtils;
import com.mm.back.utils.JsonUtils;
import com.mm.back.vo.UserInfoCacheVo;

/**
 * @author chenyanlong
 * @ClassName: AuthorityService
 * @Description: 权限
 * @date 2015年11月23日 下午3:53:42
 */
@Service("authorityService")
public class AuthorityService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthorityService.class);
    @Resource(name = "authorityDao")
    private AuthorityDao authorityDao;
    @Resource(name = "userRoleDao")
    private UserRoleDao userRoleDao;
    @Resource(name = "moduleDao")
    private ModuleDao moduleDao;
    @Autowired
    private UserDao userDao;

    /**
     * @return UserInfoCacheVo 返回类型
     * @Description: 查询登录用户拥有权限的模块列表 子集授权 父级必须授权
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
        List<UserRoleEntity> userRoleEntities = userRoleDao.getUserRole(userId);
        Set<Integer> roleIds = userRoleEntities.stream().map(userRoleEntity -> userRoleEntity.getRoleId()).distinct().collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        //权限信息
        List<AuthorityEntity> authoritys = this.authorityDao.getAuthoritys(roleIds);
        Set<Integer> moduleIds = authoritys.stream().map(authorityEntity -> authorityEntity.getMenuId()).distinct().collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(moduleIds)) {
            return null;
        }
        // 用户拥有权限的所有模块
        List<MenuEntity> moduleEntities = this.moduleDao.getModules(moduleIds);
        if (CollectionUtils.isNotEmpty(moduleEntities)) {
            List<Menu> menus = AuthorityUtils.getModuleVos(moduleEntities);
            userInfoCache.setMenus(menus);
        }
        userInfoCache.setMenuIds(moduleIds);
        return userInfoCache;
    }

    /**
     * @param state
     * @return List<ModuleEntity> 返回类型
     * @Description: 查询所有可用模块
     * @author chenyanlong
     * @date 2015年11月26日 上午9:45:13
     */
    public List<Menu> getAllModules(Integer state) {
        List<MenuEntity> modules = this.moduleDao.getAllModules(state);
        if (CollectionUtils.isNotEmpty(modules)) {
            List<Menu> menus = AuthorityUtils.getModuleVos(modules);
            return menus;
        }
        return Collections.emptyList();
    }

    /**
     * @param list
     * @return AuthorityEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月16日 下午5:42:38
     */
    @Transactional()
    public void saveOrUpdateEntity(List<AuthorityEntity> list) {
        LOGGER.info("AuthorityService.saveOrUpdateEntity param={}", JsonUtils.object2Json(list));
        this.authorityDao.delByRoleId(list.get(0).getRoleId());
        for (AuthorityEntity authorityEntity : list) {
            this.authorityDao.saveOrUpdateEntity(authorityEntity);
        }
    }

    /**
     * @param authority
     * @return AuthorityEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月16日 下午5:42:38
     */
    @Transactional()
    public void saveOrUpdate(AuthorityEntity authority) {
        this.authorityDao.saveOrUpdateEntity(authority);
    }
}
