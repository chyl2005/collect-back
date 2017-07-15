package com.mm.back.constants;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/13
 * Time:19:26
 * Desc:描述该类的作用
 */
public enum MsgtypeEnum {

    text(""),
    markdown("");

    private String name;

    MsgtypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
