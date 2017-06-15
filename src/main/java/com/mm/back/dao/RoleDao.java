package com.mm.back.dao;

import java.util.List;
import java.util.Set;
import com.mm.back.common.AoData;
import com.mm.back.entity.RoleEntity;

/**
 * @author chenyanlong
 * @ClassName: RoleDao
 * @Description: 角色
 * @date 2015年11月24日 下午1:21:34
 */
public interface RoleDao {

    /**
     * @return List<RoleEntity> 返回类型
     * @Description: 是否删除的状态
     * @author chenyanlong
     * @date 2015年11月24日 下午1:13:44
     */
    AoData getAllRoles();

    List<RoleEntity> getRoleByRoleIds(Set<Integer> roleIds);

    List<RoleEntity> getAllRoleEntities();

    /**
     * @param entity
     * @return RoleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月24日 下午1:19:24
     */
    RoleEntity updateEntity(RoleEntity entity);

    /**
     * @param roleId
     * @param isDel
     * @return Boolean 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月24日 下午1:20:07
     */
    Boolean del(Integer roleId, Integer isDel);

    /**
     * @param entity
     * @return RoleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月24日 下午1:21:12
     */
    RoleEntity saveEntity(RoleEntity entity);

    /**
     * @param roleId
     * @return RoleEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月17日 上午9:14:42
     */
    RoleEntity getRoleEntity(Integer roleId);
}
