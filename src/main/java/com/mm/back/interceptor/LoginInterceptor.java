package com.mm.back.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.mm.back.common.Config;
import com.mm.back.common.User;
import com.mm.back.service.LoginInfoService;
import com.mm.back.utils.CookieUtils;
import com.mm.back.utils.PageUtil;
import com.mm.back.utils.UserUtils;
import com.mm.back.vo.UserInfoCacheVo;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:22:46
 * Desc:描述该类的作用
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private Config config;

    @Autowired
    private LoginInfoService loginInfoService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURL().toString();
        String login = CookieUtils.getCookieValue(request, config.getLoginkey());
        UserInfoCacheVo userInfoCache = this.loginInfoService.getLoginInfo(login);
        if (userInfoCache == null || CollectionUtils.isEmpty(userInfoCache.getMenus())) {
            response.sendRedirect(config.getLoginUrl());
            return false;
        }

        String startRow = request.getParameter("startRow");
        if (StringUtils.isNotBlank(startRow)) {
            int start = Integer.parseInt(startRow);
            PageUtil.setStartRow(start);
        }
        String ps = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(ps)) {//请求中有传入pageSize
            int pageSize = Integer.parseInt(ps);
            request.getSession().setAttribute("pageSize", pageSize);
            PageUtil.setPageSize(pageSize);
        } else {//请求中没有传入pageSize
            Integer pageSize = (Integer) request.getSession().getAttribute("pageSize");
            if (pageSize == null || pageSize.intValue() == 0) {//当session也没有
                request.getSession().setAttribute("pageSize", 10);
                pageSize = 10;
                PageUtil.setPageSize(pageSize);
            }
        }

        User user = userInfoCache.getUser();
        UserUtils.bind(user);
        UserUtils.bind(userInfoCache.getMenus());
        UserUtils.bind(userInfoCache.getMenuIds());
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        UserUtils.unbindUser();
        UserUtils.unbindUserMenus();
        UserUtils.unbindMenuIds();

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
