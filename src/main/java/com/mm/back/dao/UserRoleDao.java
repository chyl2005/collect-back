package com.mm.back.dao;

import java.util.List;
import java.util.Set;
import com.mm.back.entity.UserRoleEntity;

public interface UserRoleDao {

    List<UserRoleEntity> getUserRoleByUserIds(Set<Integer> userIds);

    /**
     * @param userId 登录用户id
     * @return List<UserRoleEntity> 返回类型
     * @Description: 根据登录用户获取所有角色
     * @author chenyanlong
     * @date 2015年11月23日 下午3:59:51
     */
    List<UserRoleEntity> getUserRole(Integer userId);

    Boolean deleteByUserIdAndRoleId(Integer userId, Integer roleId);
}
