package com.mm.back.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.back.constants.DeleteStatusEnum;
import com.mm.back.dao.LoginInfoDao;
import com.mm.back.entity.LoginInfoEntity;
import com.mm.back.service.AuthorityService;
import com.mm.back.service.LoginInfoService;
import com.mm.back.utils.JsonUtils;
import com.mm.back.vo.UserInfoCacheVo;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:22:06
 * Desc:描述该类的作用
 */
@Service
class LoginInfoServiceImpl implements LoginInfoService {
    @Autowired
    private LoginInfoDao loginInfoDao;
    @Autowired
    private AuthorityService authorityService;

    /**
     * @param loginKey
     * @param userId
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:08
     */
    @Transactional
    @Override
    public void save(String loginKey, Integer userId) {
        UserInfoCacheVo userInfoCache = authorityService.getUserInfoCache(userId);
        LoginInfoEntity loginInfo = loginInfoDao.getLoginInfo(loginKey);
        if (loginInfo == null) {
            loginInfo = new LoginInfoEntity();
            loginInfo.setLoginKey(loginKey);
            loginInfo.setLoginInfo(JsonUtils.object2Json(userInfoCache));
            loginInfo.setGmtCreated(new Date());
            loginInfo.setGmtModified(new Date());
            loginInfo.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            this.loginInfoDao.saveEntity(loginInfo);
        }else {
            loginInfo.setLoginInfo(JsonUtils.object2Json(userInfoCache));
            loginInfo.setGmtModified(new Date());
            loginInfo.setIsDel(DeleteStatusEnum.NOT_DEL.getCode());
            this.loginInfoDao.updateEntity(loginInfo);
        }

    }

    /**
     * @param loginKey
     * @Description:
     * @author chenyanlong
     * @date 2015年11月26日 上午9:20:12
     */
    @Transactional
    @Override
    public void del(String loginKey) {
        loginInfoDao.del(loginKey);
    }

    /**
     * @param loginKey@Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:50:25
     */
    @Override
    public UserInfoCacheVo getLoginInfo(String loginKey) {
        LoginInfoEntity loginInfo = this.loginInfoDao.getLoginInfo(loginKey);
        if (loginInfo == null) {
            return null;
        }
        return JsonUtils.json2Object(loginInfo.getLoginInfo(), UserInfoCacheVo.class);
    }

}
