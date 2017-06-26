package com.mm.back.dao;

import java.util.List;
import java.util.Set;
import com.mm.back.entity.AuthorityEntity;

/**
 * @author chenyanlong
 * @ClassName: AuthorityDao
 * @Description: 权限
 * @date 2015年11月23日 下午3:05:50
 */
public interface AuthorityDao {

    /**
     * @param roleId
     * @param moduleId 模块id
     * @return AuthorityEntity 返回类型
     * @Description: 查询权限
     * @author chenyanlong
     * @date 2015年11月23日 下午3:27:58
     */
    AuthorityEntity getAuthority(int roleId, Integer moduleId);

    /**
     * @param roleId 模块id
     * @return AuthorityEntity 返回类型
     * @Description: 查询权限
     * @author chenyanlong
     * @date 2015年11月23日 下午3:27:58
     */
    List<AuthorityEntity> getAuthority(int roleId);

    /**
     * @param roleId
     * @return List<AuthorityEntity> 返回类型
     * @Description: 权限列表
     * @author chenyanlong
     * @date 2015年11月23日 下午4:09:27
     */
    List<AuthorityEntity> getAuthoritys(int roleId);

    /**
     * @return List<AuthorityEntity> 返回类型
     * @Description: 权限列表
     * @author chenyanlong
     * @date 2015年11月23日 下午4:09:27
     */
    List<AuthorityEntity> getAuthoritys(Set<Integer> roleIds);

    /**
     * @param authorityEntity
     * @return AuthorityEntity 返回类型
     * @Description: 新建或者保存权限
     * @author chenyanlong
     * @date 2015年11月23日 下午3:44:51
     */

    void saveOrUpdateEntity(AuthorityEntity authorityEntity);

    void delByRoleId(Integer roleId);

}