package com.mm.back.vo;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/15
 * Time:20:04
 * Desc:描述该类的作用
 */
public class CommandVo {

    private Integer code;
    private String commond;

    public CommandVo() {
    }

    public CommandVo(Integer code, String commond) {
        this.code = code;
        this.commond = commond;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCommond() {
        return commond;
    }

    public void setCommond(String commond) {
        this.commond = commond;
    }
}
