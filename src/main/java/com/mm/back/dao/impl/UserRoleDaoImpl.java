package com.mm.back.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.mm.back.dao.UserRoleDao;
import com.mm.back.entity.UserRoleEntity;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRoleEntity> implements UserRoleDao {

    @Override
    public List<UserRoleEntity> getUserRoleByUserIds(Set<Integer> userIds) {
        return this.findIn("from UserRoleEntity where userId in(:userId)", "userId", userIds);
    }

    /**
     * @param userId 登录用户id
     * @return List<UserRoleEntity> 返回类型
     * @Description: 根据登录用户获取所有角色
     * @author chenyanlong
     * @date 2015年11月23日 下午3:59:51
     */
    @Override
    public List<UserRoleEntity> getUserRole(Integer userId) {
        return this.find("from  UserRoleEntity where userId=?", userId);
    }
    @Override
    public UserRoleEntity saveEntity(UserRoleEntity entity) {
        entity.setGmtCreated(new Date());
        entity.setGmtModified(new Date());
        this.save(entity);
        return entity;
    }

    @Override
    public Boolean deleteByUserIdAndRoleId(Integer userId, Integer roleId) {
        Integer res = this.executeHql("delete UserRoleEntity where userId=? and roleId=?", userId, roleId);
        return res > 0 ? true : false;

    }
}
