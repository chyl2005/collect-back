package com.mm.back.dao.impl;

import java.util.Date;
import org.springframework.stereotype.Repository;
import com.mm.back.common.DeleteEnum;
import com.mm.back.dao.LoginInfoDao;
import com.mm.back.entity.LoginInfoEntity;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:21:22
 * Desc:描述该类的作用
 */
@Repository("loginInfoDao")
public class LoginInfoDaoImpl extends BaseDaoImpl<LoginInfoEntity> implements LoginInfoDao {
    /**
     * @param entity
     * @return LoginInfoEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    @Override
    public void updateEntity(LoginInfoEntity entity) {
        this.update(entity);
    }

    /**
     * @param entity
     * @return LoginInfoEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    @Override
    public void saveEntity(LoginInfoEntity entity) {
        this.save(entity);
    }

    /**
     * @param loginKey
     * @return LoginInfoEntity    返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:12
     */
    @Override
    public void del(String loginKey) {
        LoginInfoEntity entity = this.findFirst("from LoginInfoEntity where loginKey=?", loginKey);
        if (entity != null) {
            entity.setGmtModified(new Date());
            entity.setIsDel(DeleteEnum.DEL.getCode());
            this.update(entity);
        }
    }

    /**
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:25
     */
    @Override
    public LoginInfoEntity getLoginInfo(String loginKey) {
        LoginInfoEntity entity = this.findFirst("from LoginInfoEntity where loginKey=?", loginKey);
        return entity;
    }
}
