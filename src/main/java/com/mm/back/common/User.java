package com.mm.back.common;

import java.util.List;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:22:46
 * Desc:描述该类的作用
 */
public class User {
    private int id;
    private String login;
    private String name;
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
