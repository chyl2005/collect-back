package com.mm.back.service;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.common.AoData;
import com.mm.back.common.ConvertUtils;
import com.mm.back.common.Role;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.RoleDao;
import com.mm.back.dao.UserDao;
import com.mm.back.dao.UserRoleDao;
import com.mm.back.entity.RoleEntity;
import com.mm.back.entity.UserEntity;
import com.mm.back.entity.UserRoleEntity;
import com.mm.common.utils.MD5Utils;
import com.mm.back.vo.UserVo;

@Service("userService")
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;

    public UserEntity login(String name, String password) {
        return this.userDao.login(name, password);
    }

    /**
     * @param state
     * @return AoData    返回类型
     * @Description: 用户信息列表
     * @author chenyanlong
     * @date 2016年5月31日 上午10:23:11
     */
    public AoData getUserList(Integer state) {
        AoData<List<UserEntity>> aoData = this.userDao.getUserList(state);
        List<UserEntity> userList = aoData.getDatas();
        Set<Integer> userIds = userList.stream().map(user -> user.getId()).distinct().collect(Collectors.toSet());
        HashMap<Integer, ArrayList<Integer>> userRoleMap = new HashMap<Integer, ArrayList<Integer>>();
        //关联表  一个用户多个角色
        List<UserRoleEntity> userRoleEntities = this.userRoleDao.getUserRoleByUserIds(userIds);
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
        List<RoleEntity> roleEntities = this.roleDao.getRoleByRoleIds(roleIds);
        Map<Integer, RoleEntity> roleMap = roleEntities.stream().collect(Collectors.toMap(role -> role.getId(), role -> role));
        List<UserVo> userVos = new ArrayList<>();
        //组装数据
        for (UserEntity user : userList) {
            UserVo userVo = ConvertUtils.parseToUserResponse(user);
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
            userVo.setRoleName(roleNames);
            userVos.add(userVo);
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
    public void saveOrUpdate(UserEntity entity) {
        String userName = entity.getUserName();
        String password = entity.getPassword();
        String pwd = MD5Utils.getMD5(userName + password);
        entity.setPassword(pwd);
        entity.setGmtModified(new Date());
        if (entity.getId() != null) {
            this.userDao.updateEntity(entity);
        } else {
            entity.setGmtCreated(new Date());
            this.userDao.saveEntity(entity);
        }
    }

    /**
     * @param userId
     * @return SystemUserEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:48
     */
    public UserEntity getUserInfo(Integer userId) {
        return this.userDao.getUserInfo(userId);
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

        return this.userDao.updateEntity(entity);
    }

    @Transactional
    public Boolean changePassword(Integer userId, String newPassword, String oldPassword) {

        UserEntity userEntity = this.userDao.getUserInfo(userId);
        if (userEntity != null) {
            String pwd = MD5Utils.getMD5(userEntity.getUserName() + oldPassword);
            if (pwd.equals(userEntity.getPassword())) {
                String password = MD5Utils.getMD5(userEntity.getUserName() + newPassword);
                userEntity.setPassword(password);
                this.userDao.updateEntity(userEntity);
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
        this.userDao.updateState(id, DeleteStatusEnum.DEL.getCode());
    }

    /**
     * @param userId
     * @return List<RoleVo> 返回类型
     * @Description: 所有角色的用户权限信息
     * @author chenyanlong
     * @date 2016年3月21日 下午3:31:13
     */
    public List<Role> getRoles(Integer userId) {
        List<UserRoleEntity> userRoles = this.userRoleDao.getUserRole(userId);

        Set<Integer> roleIds = userRoles.stream().map(userRoleEntity -> userRoleEntity.getRoleId()).distinct().collect(Collectors.toSet());

        List<RoleEntity> roleEntities = this.roleDao.getAllRoleEntities();
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
            UserRoleEntity saveEntity = this.userRoleDao.saveEntity(entity);
            return saveEntity != null ? true : false;
        } else {// 删除操作
            Boolean flag = this.userRoleDao.deleteByUserIdAndRoleId(userId, roleId);
            return flag;
        }
    }

}
