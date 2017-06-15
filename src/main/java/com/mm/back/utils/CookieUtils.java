package com.mm.back.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:23:13
 * Desc:描述该类的作用
 */
public class CookieUtils {

    /**
     * @param request
     * @param @param  key
     * @return String 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月10日 下午6:36:14
     */
    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String value = cookie.getValue();
            if (cookie.getName().equals(key) && StringUtils.isNotBlank(value)) {
                return value;
            }
        }
        return null;
    }
}
