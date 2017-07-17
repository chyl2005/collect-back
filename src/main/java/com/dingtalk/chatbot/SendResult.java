package com.dingtalk.chatbot;

import com.mm.common.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;


public class SendResult {
    private boolean isSuccess;
    private Integer errcode;
    private String errmsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String toString(){
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("errorCode", errcode);
        items.put("errorMsg", errmsg);
        items.put("isSuccess", isSuccess);
        return JsonUtils.object2Json(items);
    }
}
