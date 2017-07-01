package com.mm.back.dto;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/1
 * Time:09:40
 * Desc:设备存储信息
 */
public class StoreInfoDto {


    private  Integer totalNum ;
    private  Integer currentNum ;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }
}
