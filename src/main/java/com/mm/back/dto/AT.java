package com.mm.back.dto;

import java.util.List;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/13
 * Time:19:32
 * Desc:描述该类的作用
 */
public class AT {

    private List<String> atMobiles;

    private boolean isAtAll = false;

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public boolean isAtAll() {
        return isAtAll;
    }

    public void setAtAll(boolean atAll) {
        isAtAll = atAll;
    }
}
