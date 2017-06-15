package com.mm.back.dao;

import com.mm.back.entity.LoginInfoEntity;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/10
 * Time:21:05
 * Desc:描述该类的作用
 */
public interface LoginInfoDao {


    /**
     * @param entity
     * @return LoginInfoEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    void updateEntity(LoginInfoEntity entity) ;


    /**
     * @param entity
     * @return LoginInfoEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    void saveEntity(LoginInfoEntity entity) ;

    /**
     * @param loginKey
     * @return LoginInfoEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:12
     */
    void del(String loginKey);

    /**
     * @param @param userId
     * @return LoginInfoEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:25
     */
    LoginInfoEntity getLoginInfo(String loginKey);
}
