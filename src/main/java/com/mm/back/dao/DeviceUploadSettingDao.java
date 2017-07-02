package com.mm.back.dao;

import com.mm.back.entity.DeviceUploadSettingEntity;

/**
 * Author:chyl2005
 * Date:17/6/15
 * Time:11:16
 * Desc:设备配置信息
 */
public interface DeviceUploadSettingDao {





    /**
     * @param setting
     */
    void insertOrUpdate(DeviceUploadSettingEntity setting);


    DeviceUploadSettingEntity getSetting(Integer deviceId);

}
