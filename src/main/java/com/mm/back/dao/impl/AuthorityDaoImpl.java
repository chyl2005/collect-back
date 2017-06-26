package com.mm.back.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.AuthorityDao;
import com.mm.back.entity.AuthorityEntity;

/**
 * @author chenyanlong
 * @ClassName: AuthorityDao
 * @Description: 权限
 * @date 2015年11月23日 下午3:05:50
 */
@Repository("authorityDao")
public class AuthorityDaoImpl extends BaseDaoImpl<AuthorityEntity> implements AuthorityDao {

    /**
     * @param roleId
     * @param menuId 模块id
     * @return AuthorityEntity 返回类型
     * @Description: 查询权限
     * @author chenyanlong
     * @date 2015年11月23日 下午3:27:58
     */
    @Override
    public AuthorityEntity getAuthority(int roleId, Integer menuId) {
        return this.findFirst("from AuthorityEntity where   roleId=? and menuId=?", roleId, menuId);
    }

    /**
     * @param roleId 模块id
     * @return AuthorityEntity 返回类型
     * @Description: 查询权限
     * @author chenyanlong
     * @date 2015年11月23日 下午3:27:58
     */
    @Override
    public List<AuthorityEntity> getAuthority(int roleId) {
        return this.find("from AuthorityEntity where   roleId=?", roleId);
    }

    /**
     * @param roleId
     * @return List<AuthorityEntity> 返回类型
     * @Description: 权限列表
     * @author chenyanlong
     * @date 2015年11月23日 下午4:09:27
     */
    @Override
    public List<AuthorityEntity> getAuthoritys(int roleId) {
        return this.find("from AuthorityEntity where roleId=? ", roleId);
    }

    /**
     * @return List<AuthorityEntity> 返回类型
     * @Description: 权限列表
     * @author chenyanlong
     * @date 2015年11月23日 下午4:09:27
     */
    @Override
    public List<AuthorityEntity> getAuthoritys(Set<Integer> roleIds) {
        if (CollectionUtils.isNotEmpty(roleIds)) {
            return this.findIn("from AuthorityEntity where   roleId in(:ids) ", "ids", roleIds);
        }
        return Collections.emptyList();
    }

    /**
     * @param authorityEntity
     * @return AuthorityEntity 返回类型
     * @Description: 新建或者保存权限
     * @author chenyanlong
     * @date 2015年11月23日 下午3:44:51
     */
    @Override
    public void saveOrUpdateEntity(AuthorityEntity authorityEntity) {
        AuthorityEntity authority = this.getAuthority(authorityEntity.getRoleId(), authorityEntity.getMenuId());
        if (authority == null) {
            authorityEntity.setGmtModified(new Date());
            authorityEntity.setGmtCreated(new Date());
            authorityEntity.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            this.save(authorityEntity);
        } else {
            authority.setGmtModified(new Date());
            authority.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            this.update(authority);
        }
    }

    @Override
    public void delByRoleId(Integer roleId) {
        this.executeHql("update AuthorityEntity set isDel=1  where  roleId=?", roleId);
    }
}
