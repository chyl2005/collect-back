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

    QUERY_NEW_DATA1(100, "JSON查询最新数据", ""),
    QUERY_NEW_DATA2(101, "JSON查询最新数据", ""),
    QUERY_DATA(102, "JSON查询数据：@num", ""),
    QUERY_PARAM(104, "JSON查询全部参数", ""),
    QUERY_STORE_INFO(103, "JSON查询储存信息", ""),
    HISTORY(5, "导出历史数据", ""),
    SET_WELL_NUM(6, "设置井号", "井号设置成功，当前井号"),
    SET_SURFACE_HIGH(7, "设置地表高程", "设置地表高程成功，当前参考地表高程"),
    SET_SENSOR_DEPTH(8, "设置传感器埋深", "设置传感器埋深成功，当前参考传感器埋深"),
    SET_LINE(9, "设置线性修正系数", "当前线性修正系数"),
    SET_TIME(10, "设定时间", "设定时间成功"),
    SET_WAKEUP(11, "设置唤醒时间1：@wakeupTime1 /设置唤醒时间2：@wakeupTime2", "设置唤醒时间成功，当前唤醒时间"),
    SET_PORT(12, "设置端口号", "端口号修改成功"),
    SET_IP(13, "设置IP地址", "端口号修改成功"),
    SET_PHONE1(14, "设置电话1号码", "电话号码设置成功"),
    SET_PHONE2(15, "设置电话2号码", "电话号码设置成功"),
    QUERY_DEVICE_NUM(16, "查询硬件序列号", "硬件序列号");

    public static final List<String> commonds = new ArrayList<>();

    static {


        commonds.add(QUERY_NEW_DATA1.commond);
        commonds.add(SET_WELL_NUM.commond);
        commonds.add(SET_SURFACE_HIGH.commond);
        commonds.add(SET_SENSOR_DEPTH.commond);
        commonds.add(SET_LINE.commond);
        commonds.add(SET_TIME.commond);
        commonds.add(SET_WAKEUP.commond);
        commonds.add(SET_PHONE1.commond);
        commonds.add(SET_PHONE2.commond);

    }


    /**
     * 命令分隔符
     */
    public static final String SPILT = "：";
    /**
     * 指令编号
     */
    private Integer code;
    private String commond;

    private String success;

    CommandEnum(Integer code, String commond, String success) {
        this.code = code;
        this.commond = commond;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public String getCommond() {
        return commond;
    }

    public String getSuccess() {
        return success;
    }
}
