package com.mm.back.constants;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:12:21
 * Desc:描述该类的作用
 */
public enum ClientConst {

    DEVICE_NUM("硬件序列号")

    ;

    private String name;

    ClientConst(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
