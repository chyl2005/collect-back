package com.mm.back.service;


import com.mm.back.vo.UserInfoCacheVo;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:22:04
 * Desc:描述该类的作用
 */

public interface LoginInfoService {




    /**
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    void save(String loginKey,Integer userId) ;
    /**
     * @param loginKey
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:12
     */
    void del(String loginKey);

    /**
     * @param @param userId
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:25
     */
    UserInfoCacheVo getLoginInfo(String loginKey);

}
