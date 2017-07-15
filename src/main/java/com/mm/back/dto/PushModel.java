package com.mm.back.dto;

import com.mm.back.constants.MsgtypeEnum;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/13
 * Time:19:12
 * Desc:描述该类的作用
 */
public class PushModel {

    /**
     * 消息类型
     *
     * @MsgtypeEnum
     */
    private String msgtype = MsgtypeEnum.text.name();

    /**
     * 小溪内容
     */
    private String text;

    /**
     * @消息提醒
     */
    private AT at;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AT getAt() {
        return at;
    }

    public void setAt(AT at) {
        this.at = at;
    }
}
