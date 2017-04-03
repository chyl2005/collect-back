package com.mm.back.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/4/3
 * Time:09:51
 * Desc:描述该类的作用
 */
@Service
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        // 收到消息直接打印输出
        LOGGER.info(channelHandlerContext.channel().remoteAddress() + " Say : " + message);
        // 返回客户端消息 - 我已经接收到了你的消息
        channelHandlerContext.writeAndFlush("Received your message :" + message + "!\n");
    }

    /*
  *
  * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
  *
  * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
  * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        LOGGER.info("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
