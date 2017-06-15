package com.mm.back.dao;


import java.util.List;
import com.mm.back.common.AoData;
import com.mm.back.entity.UserEntity;

public interface UserDao  {

    /**
     * @param isDel
     * @return AoData 返回类型
     * @Description: 用户列表
     * @author chenyanlong
     * @date 2015年11月25日 下午3:16:09
     */
     AoData getUserList(Integer isDel) ;

     UserEntity login(String name, String password) ;

    /**
     * @param entity
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
     UserEntity saveEntity(UserEntity entity) ;

    /**
     * @param entity
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:12
     */
     UserEntity updateEntity(UserEntity entity);

    /**
     * @param @param userId
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:25
     */
     UserEntity getUserInfo(Integer userId);
    /**
     * @param id
     * @param isDel
     * @return UserEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月21日 下午6:17:31
     */
     UserEntity updateState(Integer id, Integer isDel) ;

     Boolean deleteEntity(Integer id) ;

     Boolean changePassword(UserEntity entity) ;

     List<UserEntity> getAllUserList();
}
