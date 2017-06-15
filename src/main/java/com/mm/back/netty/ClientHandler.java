package com.mm.back.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author:chyl2005
 * Date:17/4/3
 * Time:10:43
 * Desc:描述该类的作用
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        LOGGER.info("Server say : " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
}
