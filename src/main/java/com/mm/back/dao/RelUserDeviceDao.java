package com.mm.back.dao;

import java.util.List;
import com.mm.back.entity.RelUserDeviceEntity;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/29
 * Time:22:29
 * Desc:描述该类的作用
 */
public interface RelUserDeviceDao {


    List<RelUserDeviceEntity>  getUserDevices(Integer userId);
}
