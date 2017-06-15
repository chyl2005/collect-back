package com.mm.back.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:chyl2005
 * Date:17/4/22
 * Time:10:35
 * Desc:描述该类的作用
 */
public enum CommandEnum {

    NEW_DATA(1, "JSON查询最新数据"),
    DATA(2, "JSON查询数据"),
    PARAM(3, "JSON查询全部参数"),
    STORE_INFO(4, "JSON查询储存信息"),
    HISTORY(5, "导出历史数据"),
    SET_WELL_NUM(6, "设置井号"),
    SET_HIGH(7, "设置地表高程："),
    SET_RATIO(8, "设置线性修正系数："),
    SET_TIME(9, "设定时间 "),
    WAKEUP(10, " 设置唤醒时间1：@ /设置唤醒时间2：@"),
    QUERY_DEVICE_NUM(11, "查询硬件序列号")
    ;

    public static List<String> commonds = new ArrayList<>();

    static {
        commonds.add(NEW_DATA.name);
        commonds.add(DATA.name);
        commonds.add(PARAM.name);
        commonds.add(STORE_INFO.name);
        commonds.add(HISTORY.name);
        commonds.add(HISTORY.name);
        commonds.add(HISTORY.name);

    }

    private Integer code;
    private String name;

    CommandEnum(Integer code, String name) {
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
