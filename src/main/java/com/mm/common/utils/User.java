package com.mm.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/12
 * Time:23:13
 * Desc:描述该类的作用
 */
public class User {

    @JsonProperty(value = "Name")
    private String Name;
    @JsonProperty(value = "IPAddress")
    private String IPAddress;
    @JsonProperty(value = "Name")
    public String getName() {
        return Name;
    }
    @JsonProperty(value = "Name")
    public void setName(String name) {
        Name = name;
    }

    @JsonProperty(value = "IPAddress")
    public String getIPAddress() {
        return IPAddress;
    }

    @JsonProperty(value = "IPAddress")
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setIPAddress("123.456");
        user.setName("2222");
        System.out.println(new ObjectMapper().writeValueAsString(user));
        System.out.println(JsonUtils.object2Json(user));
    }
}
