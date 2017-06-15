package com.mm.back.dao.impl;


import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.mm.back.dao.AuthorityDao;
import com.mm.back.entity.AuthorityEntity;

/**
 * 
 * @ClassName: AuthorityDao
 * @Description: 权限
 * @author chenyanlong
 * @date 2015年11月23日 下午3:05:50
 */
@Repository("authorityDao")
public class AuthorityDaoImpl extends BaseDaoImpl<AuthorityEntity> implements AuthorityDao{

	/**
	 * 
	 * @Description: 查询权限
	 * @param roleId
	 * @param moduleId
	 *            模块id
	 * @return AuthorityEntity 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午3:27:58
	 */
	public AuthorityEntity getAuthority(int roleId, String moduleId) {
		return this.findFirst("from AuthorityEntity where   roleId=?,moduleId=?", roleId, moduleId);
	}

	/**
	 * 
	 * @Description: 查询权限
	 * @param roleId
	 *            模块id
	 * @return AuthorityEntity 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午3:27:58
	 */
	public AuthorityEntity getAuthority(int roleId) {
		return this.findFirst("from AuthorityEntity where   roleId=?", roleId);
	}
	
	/**
	 * 
	 * @Description: 权限列表
	 * @param roleId
	 * @return List<AuthorityEntity> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午4:09:27
	 */
	public List<AuthorityEntity> getAuthoritys(int roleId) {
		return this.find("from AuthorityEntity where roleId=? ",  roleId);
	}
	
	/**
	 * 
	 * @Description: 权限列表
	 * @return List<AuthorityEntity> 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午4:09:27
	 */
	public List<AuthorityEntity> getAuthoritys(Set<Integer> roleIds) {
		return this.findIn("from AuthorityEntity where   roleId in(:ids) ", "ids", roleIds);
	}

	/**
	 * 
	 * @Description: 新建或者保存权限
	 * @param authorityEntity
	 * @return AuthorityEntity 返回类型
	 * @author chenyanlong
	 * @date 2015年11月23日 下午3:44:51
	 */
	public AuthorityEntity saveOrUpdateEntity(AuthorityEntity authorityEntity) {
		AuthorityEntity authority = getAuthority(authorityEntity.getRoleId());
		if (authority == null) {
			this.save(authorityEntity);
			return authorityEntity;
		}else {
			authority.setModuleId(authorityEntity.getModuleId());
			this.update(authority);
			return authority;
		}
	}
}
