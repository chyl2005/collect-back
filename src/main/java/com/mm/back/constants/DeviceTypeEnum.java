package com.mm.back.constants;

/**
 * Author:chyl2005
 * Date:17/4/23
 * Time:11:34
 * Desc:描述该类的作用
 */
public enum DeviceTypeEnum {

    WELL(1, "矿井");
    private Integer code;
    private String name;

    DeviceTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
