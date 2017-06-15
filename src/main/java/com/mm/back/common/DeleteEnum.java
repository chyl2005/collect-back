package com.mm.back.common;

/**
 * Author:chyl2005
 * Date:17/6/10
 * Time:21:31
 * Desc:描述该类的作用
 */
public enum DeleteEnum {


    NOT_DEL(0, "未删除"),
    DEL(1, "删除");


    private Integer code;
    private String name;

    DeleteEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }


    public static String getName(int code) {
        for (DeleteEnum statusEnum : values()) {
            if (code == statusEnum.code) {
                return statusEnum.name;
            }
        }
        return null;
    }

}
