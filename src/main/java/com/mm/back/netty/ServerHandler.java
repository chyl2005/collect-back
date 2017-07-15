package com.mm.back.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mm.back.service.HandlerMessageService;

/**
 * Author:chyl2005
 * Date:17/4/3
 * Time:09:51
 * Desc:描述该类的作用
 */
@Service
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Map<String, Integer> clientOKNum = new HashMap<>();

    public static Map<String, Integer> clientErrorNum = new HashMap<>();

    public static Map<String, Integer> clientInstallNum = new HashMap<>();
    /**
     * ip地址-设备硬件编号映射
     */
    public static Map<String, String> addressToDeviceNumMap = new ConcurrentHashMap<>();


    @Autowired
    private HandlerMessageService messageService;

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        //        Channel incoming = ctx.channel();
        //        for (Channel channel : channels) {
        //            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        //        }
        //设备连接时  查询设备参数
        //        sendDelayMessageService.send(channelHandlerContext, CommandEnum.QUERY_PARAM.getCommond() );
        //清空客户端连接数据
        clearConnetionInfo(channelHandlerContext.channel().remoteAddress().toString());
        LOGGER.info("客户端ip={} 新增", channelHandlerContext.channel().remoteAddress().toString());
        channels.add(channelHandlerContext.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //        Channel incoming = ctx.channel();
        //                for (Channel channel : channels) {
        //                    channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        //                }
        LOGGER.info("客户端ip={} 断开", ctx.channel().remoteAddress().toString());
        channels.remove(ctx.channel());
        //移除 地址到 设备号映射
        clearConnetionInfo(ctx.channel().remoteAddress().toString());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        //命令编号
        String address = channelHandlerContext.channel().remoteAddress().toString();
        String deviceNum = addressToDeviceNumMap.get(address);
        LOGGER.info("ServerHandler.channelRead0 address={}  deviceNum={} message={}", address, deviceNum, message);

        String noWhitespaceMessage = StringUtils.deleteWhitespace(message);
        if (message.contains("commandNum")) {
            messageService.handlerMessage(noWhitespaceMessage, address);
        }
        //正常连接
        if (message.contains("OK")) {
            messageService.sendMessage(channelHandlerContext);
        }
        //客户端异常
        if (message.contains("RX8025")) {
            messageService.clientError(channelHandlerContext);
        }
        //安装模式
        if (message.contains("installation")) {
            messageService.clientInstall(channelHandlerContext);
        }

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

        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostAddress() + " service!");

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //移除 地址到 设备号映射
        clearConnetionInfo(ctx.channel().remoteAddress().toString());
        //super.exceptionCaught(ctx, cause);
        ctx.writeAndFlush("server error");
        LOGGER.error("ServerHandler.exceptionCaught  ip={}", ctx.channel().remoteAddress().toString(), cause);

    }

    /**
     * 清空客户端连接数据
     *
     * @param address
     */
    private void clearConnetionInfo(String address) {
        addressToDeviceNumMap.remove(address);
        clientOKNum.remove(address);
        clientErrorNum.remove(address);
        clientInstallNum.remove(address);
    }

}
