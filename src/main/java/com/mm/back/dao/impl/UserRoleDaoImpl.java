package com.mm.back.dao.impl;


import java.io.Serializable;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.mm.back.dao.UserRoleDao;
import com.mm.back.entity.UserRoleEntity;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRoleEntity> implements UserRoleDao{

	public List<UserRoleEntity> getUserRoleByUserIds(Set<Integer> userIds) {
		return this.findIn("from UserRoleEntity where userId in(:userId)", "userId", userIds);
	}

	/**
	 * 
	 * @Description: 根据登录用户获取所有角色
	 * @param userId
	 *            登录用户id
	 * @return List<UserRoleEntity> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午3:59:51
	 */
	public List<UserRoleEntity> getUserRole(Integer userId) {
		return this.find("from  UserRoleEntity where userId=?", userId);
	}

	public UserRoleEntity saveEntity(UserRoleEntity entity) {
		Serializable save = this.save(entity);
		return entity;
	}

	public Boolean deleteByUserIdAndRoleId(Integer userId, Integer roleId) {
		Integer res = this.executeHql("delete UserRoleEntity where userId=? and roleId=?", userId, roleId);
		return res > 0 ? true : false;

	}
}
