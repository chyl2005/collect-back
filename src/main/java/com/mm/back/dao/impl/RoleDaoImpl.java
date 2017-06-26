package com.mm.back.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import com.mm.back.common.AoData;
import com.mm.back.dao.RoleDao;
import com.mm.back.entity.RoleEntity;

/**
 * @author chenyanlong
 * @ClassName: RoleDao
 * @Description: 角色
 * @date 2015年11月24日 下午1:21:34
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<RoleEntity> implements RoleDao {

    /**
     * @return List<RoleEntity> 返回类型
     * @Description: 是否删除的状态
     * @author chenyanlong
     * @date 2015年11月24日 下午1:13:44
     */
    public AoData<List<RoleEntity>> getAllRoles() {
        AoData aoData = this.findPage(null, "from RoleEntity");
        return aoData;
    }

    public List<RoleEntity> getRoleByRoleIds(Set<Integer> roleIds) {
        if (CollectionUtils.isNotEmpty(roleIds)) {
            return this.findIn("from RoleEntity where id in(:ids)", "ids", roleIds);
        }
        return Collections.emptyList();
    }

    public List<RoleEntity> getAllRoleEntities() {
        return this.find("from RoleEntity");
    }

    /**
     * @param entity
     * @return RoleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月24日 下午1:19:24
     */
    public RoleEntity updateEntity(RoleEntity entity) {
        this.update(entity);
        return entity;
    }

    /**
     * @param roleId
     * @param isDel
     * @return Boolean 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月24日 下午1:20:07
     */
    public Boolean del(Integer roleId, Integer isDel) {
        RoleEntity roleEntity = this.get(RoleEntity.class, roleId);
        roleEntity.setIsDel(isDel);
        roleEntity.setGmtModified(new Date());
        this.update(roleEntity);
        return false;
    }

    /**
     * @param entity
     * @return RoleEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月24日 下午1:21:12
     */
    public RoleEntity saveEntity(RoleEntity entity) {
        this.save(entity);
        return entity;
    }

    /**
     * @return RoleEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月17日 上午9:14:42
     */
    public RoleEntity getRoleEntity(Integer id) {
        return this.get(RoleEntity.class, id);
    }
}
