package com.mm.back.service;

import java.util.List;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/2
 * Time:23:29
 * Desc:描述该类的作用
 */
public interface HandlerMessageService {


    void handlerMessage(String message, String address);


    List<String> sendMessage(String deviceNum);
}
