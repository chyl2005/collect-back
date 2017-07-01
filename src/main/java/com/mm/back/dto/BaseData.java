package com.mm.back.dto;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/1
 * Time:09:15
 * Desc:描述该类的作用
 */
public class BaseData<T> {

    /**
     *@see com.mm.back.constants.CommandEnum
     */
    private Integer commandNum;
    private T data;

    public Integer getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(Integer commandNum) {
        this.commandNum = commandNum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
