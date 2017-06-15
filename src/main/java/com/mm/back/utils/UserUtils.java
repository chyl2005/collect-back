package com.mm.back.utils;

import java.util.List;
import java.util.Set;
import com.mm.back.common.Menu;
import com.mm.back.common.ThreadContext;
import com.mm.back.common.User;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:22:39
 * Desc:登录用户信息
 */
public class UserUtils {

    private static final String USER_KEY = "user";
    private static final String USER_MENUS_KEY = "user.menus";
    private static final String MENUS_ID_KEY = "user.menuIds";

    public static User getUser() {
        return (User) ThreadContext.get(USER_KEY);
    }

    public static void bind(User user) {
        if (user != null && user.getId() > 0) {
            ThreadContext.put(USER_KEY, user);
        }
    }

    public static void unbindUser() {
        ThreadContext.remove(USER_KEY);
    }

    public static List<Menu> getMenus() {
        return (List<Menu>) ThreadContext.get(USER_MENUS_KEY);
    }

    public static void bind(List<Menu> menus) {
        if (menus != null) {
            ThreadContext.put(USER_MENUS_KEY, menus);
        }
    }

    public static void unbindUserMenus() {
        ThreadContext.remove(USER_MENUS_KEY);
    }

    public static Set<Integer> getMenuIds() {
        return (Set<Integer>) ThreadContext.get(MENUS_ID_KEY);
    }

    public static void bind(Set<Integer> menuIds) {
        if (menuIds != null) {
            ThreadContext.put(MENUS_ID_KEY, menuIds);
        }
    }

    public static void unbindMenuIds() {
        ThreadContext.remove(MENUS_ID_KEY);
    }
}
