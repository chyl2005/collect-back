package com.mm.back.service;

import java.util.List;
import com.mm.back.constants.CommandEnum;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/2
 * Time:23:29
 * Desc:描述该类的作用
 */
public interface HandlerMessageService {


    List<CommandEnum>  handlerMessage(String message, String address);


    List<String> sendMessage(String deviceNum);
}
