package com.mm.back.service;

import io.netty.channel.ChannelHandlerContext;

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


    void sendMessage(ChannelHandlerContext channelHandlerContext);

    /**
     * 处理客户端异常    客户端持续发送 RX8025 anomaly
     * @param channelHandlerContext
     */
    void  clientError(ChannelHandlerContext channelHandlerContext);

    /**
     * 安装模式
     * @param channelHandlerContext
     */
    void  clientInstall(ChannelHandlerContext channelHandlerContext);


    void sendCommond(String address,Integer command,String message);
}
