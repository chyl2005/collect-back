package com.mm.back.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mm.back.dao.RelUserDeviceDao;
import com.mm.back.entity.RelUserDeviceEntity;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/29
 * Time:22:30
 * Desc:描述该类的作用
 */

@Repository
public class RelUserDeviceDaoImpl extends BaseDaoImpl<RelUserDeviceEntity> implements RelUserDeviceDao {

    @Override
    public List<RelUserDeviceEntity> getUserDevices(Integer userId) {
        StringBuilder hql = new StringBuilder();
        hql.append("from ").append(RelUserDeviceEntity.class.getSimpleName()).append(" where 1=1");
        hql.append("and userId=? ");
        return this.find(hql.toString(), userId);
    }

}
