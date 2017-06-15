package com.mm.back.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import com.mm.back.common.AoData;
import com.mm.back.dao.UserDao;
import com.mm.back.entity.UserEntity;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao {

    /**
     * @param isDel
     * @return AoData 返回类型
     * @Description: 用户列表
     * @author chenyanlong
     * @date 2015年11月25日 下午3:16:09
     */
    @Override
    public AoData<List<UserEntity>> getUserList(Integer isDel) {
        HashMap<String, Object> paras = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder();
        hql.append("from UserEntity");
        if (isDel != null) {
            paras.put("isDel", isDel);
            hql.append("where isDel=:isDel");
        }
        return this.findPage(null, hql.toString(), paras);
    }

    @Override
    public UserEntity login(String name, String password) {
        List<UserEntity> list = this.find("from UserEntity where userName=? and password=?", name, password);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * @param entity
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    @Override
    public UserEntity saveEntity(UserEntity entity) {
        this.save(entity);
        return entity;
    }

    /**
     * @param entity
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:12
     */
    @Override
    public UserEntity updateEntity(UserEntity entity) {
        UserEntity userEntity = this.get(UserEntity.class, entity.getId());
        userEntity.setUserName(entity.getUserName());
        userEntity.setTrueName(entity.getTrueName());
        userEntity.setGmtModified(new Date());
        if (StringUtils.isNotBlank(entity.getPassword())) {
            userEntity.setPassword(entity.getPassword());
        }
        this.update(userEntity);
        return entity;
    }

    /**
     * @param @param userId
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:25
     */
    @Override
    public UserEntity getUserInfo(Integer userId) {
        return this.get(UserEntity.class, userId);
    }

    /**
     * @param id
     * @param isDel
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月21日 下午6:17:31
     */
    @Override
    public UserEntity updateState(Integer id, Integer isDel) {
        UserEntity userEntity = this.get(UserEntity.class, id);
        userEntity.setIsDel(isDel);
        this.update(userEntity);
        return userEntity;
    }

    @Override
    public Boolean deleteEntity(Integer id) {
        UserEntity entity = this.get(UserEntity.class, id);
        if (entity != null) {
            this.delete(entity);
        }
        return true;
    }

    @Override
    public Boolean changePassword(UserEntity entity) {
        this.update(entity);
        return true;
    }

    @Override
    public List<UserEntity> getAllUserList() {
        return this.find("from UserEntity where isDel=0");
    }
}
