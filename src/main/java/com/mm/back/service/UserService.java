package com.mm.back.service;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.common.ConvertUtils;
import com.mm.back.common.DeleteEnum;
import com.mm.back.common.Role;
import com.mm.back.dao.impl.RoleDaoImpl;
import com.mm.back.dao.impl.UserDaoImpl;
import com.mm.back.dao.impl.UserRoleDaoImpl;
import com.mm.back.entity.RoleEntity;
import com.mm.back.entity.UserEntity;
import com.mm.back.entity.UserRoleEntity;
import com.mm.back.model.UserResponse;
import com.mm.back.utils.MD5Utils;

@Service("userService")
public class UserService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private UserRoleDaoImpl userRoleDaoImpl;
    @Autowired
    private RoleDaoImpl roleDaoImpl;

    public UserEntity login(String name, String password) {
        return this.userDaoImpl.login(name, password);
    }

    /**
     * @param state
     * @return AoData    返回类型
     * @Description: 用户信息列表
     * @author chenyanlong
     * @date 2016年5月31日 上午10:23:11
     */
    public AoData getUserList(Integer state) {
        AoData<List<UserEntity>> aoData = this.userDaoImpl.getUserList(state);
        List<UserEntity> userList = aoData.getDatas();
        Set<Integer> userIds = userList.stream().map(user -> user.getId()).distinct().collect(Collectors.toSet());
        HashMap<Integer, ArrayList<Integer>> userRoleMap = new HashMap<Integer, ArrayList<Integer>>();
        //关联表  一个用户多个角色
        List<UserRoleEntity> userRoleEntities = this.userRoleDaoImpl.getUserRoleByUserIds(userIds);
        HashSet<Integer> roleIds = new HashSet<>();
        for (UserRoleEntity userRoleEntity : userRoleEntities) {
            ArrayList<Integer> roleList = userRoleMap.get(userRoleEntity.getUserId());
            if (roleList == null) {
                roleList = new ArrayList<>();
            }
            roleList.add(userRoleEntity.getRoleId());
            roleIds.add(userRoleEntity.getRoleId());
            userRoleMap.put(userRoleEntity.getUserId(), roleList);
        }
        //角色表
        List<RoleEntity> roleEntities = this.roleDaoImpl.getRoleByRoleIds(roleIds);
        Map<Integer, RoleEntity> roleMap = roleEntities.stream().collect(Collectors.toMap(role -> role.getId(), role -> role));
        List<UserResponse> userResponses = new ArrayList<>();
        //组装数据
        for (UserEntity user : userList) {
            UserResponse userResponse = ConvertUtils.parseToUserResponse(user);
            List<Integer> roles = userRoleMap.get(user.getId());
            List<String> roleNames = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(roles)) {
                for (Integer roleId : roles) {
                    RoleEntity roleEntity = roleMap.get(roleId);
                    if (null != roleEntity) {
                        roleNames.add(roleEntity.getRoleName());
                    }
                }
            }
            userResponse.setRoleName(roleNames);
            userResponses.add(userResponse);
        }
        return aoData;
    }

    /**
     * @param entity
     * @return AdminuserEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    @Transactional
    public UserEntity saveEntity(UserEntity entity) {

        return this.userDaoImpl.saveEntity(entity);
    }

    /**
     * @param userId
     * @return SystemUserEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:48
     */
    public UserEntity getUserInfo(Integer userId) {
        return this.userDaoImpl.getUserInfo(userId);
    }

    /**
     * @param entity
     * @return AdminuserEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:12
     */
    @Transactional
    public UserEntity updateEntity(UserEntity entity) {

        return this.userDaoImpl.updateEntity(entity);
    }
    @Transactional
    public Boolean changePassword(Integer userId, String newPassword, String oldPassword) {

        UserEntity userEntity = this.userDaoImpl.getUserInfo(userId);
        if (userEntity != null) {
            String pwd = MD5Utils.getMD5(userEntity.getUserName() + oldPassword);
            if (pwd.equals(userEntity.getPassword())) {
                String password = MD5Utils.getMD5(userEntity.getUserName() + newPassword);
                userEntity.setPassword(password);
                this.userDaoImpl.updateEntity(userEntity);
                return true;
            }
        }
        return false;
    }

    /**
     * @param id
     * @return UserEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月21日 下午6:18:02
     */
    @Transactional
    public void del(Integer id) {
        this.userDaoImpl.updateState(id, DeleteEnum.DEL.getCode());
    }

    /**
     * @param userId
     * @return List<RoleVo> 返回类型
     * @Description: 所有角色的用户权限信息
     * @author chenyanlong
     * @date 2016年3月21日 下午3:31:13
     */
    public List<Role> getRoles(Integer userId) {
        List<UserRoleEntity> userRoles = null;
        if (null != userId) {
            userRoles = this.userRoleDaoImpl.getUserRole(userId);

        }
        Set<Integer> roleIds = null;
        if (userRoles != null && userRoles.size() > 0) {
            roleIds = new HashSet<Integer>();
            for (UserRoleEntity userRoleEntity : userRoles) {
                roleIds.add(userRoleEntity.getRoleId());
            }
        }
        List<RoleEntity> roleEntities = this.roleDaoImpl.getAllRoleEntities();
        ArrayList<Role> roles = new ArrayList<Role>();
        for (RoleEntity roleEntity : roleEntities) {
            Role role = parseToRoleVo(roleEntity, roleIds);
            roles.add(role);
        }
        return roles;
    }

    private Role parseToRoleVo(RoleEntity roleEntity, Set<Integer> roleIds) {
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setCreateTime(roleEntity.getGmtCreated());
        if (roleIds != null && roleIds.size() > 0 && roleIds.contains(roleEntity.getId())) {
            role.setIsAuthority(1);
        } else {
            role.setIsAuthority(0);
        }
        role.setRoleName(roleEntity.getRoleName());
        role.setIsDel(roleEntity.getIsDel());
        return role;
    }

    /**
     * @param userId
     * @param roleId
     * @param operate 1 添加操作 0 删除
     * @return Boolean    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2016年3月21日 下午4:26:49
     */
    @Transactional
    public Boolean saveOrUpdateUserRole(Integer userId, Integer roleId, Integer operate) {
        // 添加操作
        if (operate == 1) {
            UserRoleEntity entity = new UserRoleEntity();
            entity.setRoleId(roleId);
            entity.setUserId(userId);
            UserRoleEntity saveEntity = this.userRoleDaoImpl.saveEntity(entity);
            return saveEntity != null ? true : false;
        } else {// 删除操作
            Boolean flag = this.userRoleDaoImpl.deleteByUserIdAndRoleId(userId, roleId);
            return flag;
        }
    }


}
