package com.mm.common.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/12
 * Time:23:13
 * Desc:描述该类的作用
 */
public class User {

    @JsonProperty(value = "IPAddress")
    private String IPAddress;

    @JsonIgnore
    public String getIPAddress() {
        return IPAddress;
    }

    @JsonIgnore
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setIPAddress("123.456");

        System.out.println(JsonUtils.object2Json(user));
    }
}
