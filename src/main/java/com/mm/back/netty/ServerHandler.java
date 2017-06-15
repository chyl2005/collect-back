package com.mm.back.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mm.back.service.HandlerMessageService;
import com.mm.back.constants.ClientConst;
import com.mm.back.constants.CommandEnum;

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

    private static Map<String, Integer> clientOKNum = new HashMap<>();

    @Autowired
    private HandlerMessageService messageService;
    /**
     * ip地址-设备硬件编号映射
     */
    private static Map<String, String> addressToDeviceNumMap = new ConcurrentHashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        //        Channel incoming = ctx.channel();
        //        for (Channel channel : channels) {
        //            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        //        }
        //设备连接时 发送获取硬件编号指令
        ctx.writeAndFlush(CommandEnum.QUERY_DEVICE_NUM);
        LOGGER.info("客户端ip={} 新增", ctx.channel().remoteAddress().toString());
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        //        Channel incoming = ctx.channel();
        //                for (Channel channel : channels) {
        //                    channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        //                }
        LOGGER.info("客户端ip={} 断开", ctx.channel().remoteAddress().toString());
        channels.remove(ctx.channel());
        //移除 地址到 设备号映射
        addressToDeviceNumMap.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        String msg = new String(message.getBytes("gbk"), "utf-8");

        //判断返回消息类型
        if (message.contains(ClientConst.DEVICE_NUM.getName())) {
            String[] split = message.split("：");
            addressToDeviceNumMap.put(channelHandlerContext.channel().remoteAddress().toString(), split[1]);
        }else {
            String deviceNum = addressToDeviceNumMap.get(channelHandlerContext.channel().remoteAddress().toString());
            messageService.handlerMessage(deviceNum,message);
        }

        // 收到消息直接打印输出
        LOGGER.info(channelHandlerContext.channel().remoteAddress() + " Say : " + message);
        // 返回客户端消息 - 我已经接收到了你的消息
        if (StringUtils.isNotBlank(message) && message.equals("OK")) {
            Integer oknum = clientOKNum.get(channelHandlerContext.channel().remoteAddress().toString());
            if (oknum == null || oknum > 5) {
                oknum = new Integer(0);
            }
            channelHandlerContext.writeAndFlush(CommandEnum.commonds.get(oknum) + "\r\n");
            oknum++;
            clientOKNum.put(channelHandlerContext.channel().remoteAddress().toString(), oknum);

        }
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

        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostAddress() + " service!\n");

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        //LOGGER.error("exceptionCaught ", cause);
        //ctx.close();
    }

    /**
     * Get host IP address
     *
     * @return IP Address
     */
    private InetAddress getAddress() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    return addresses.nextElement();
                }
            }
        } catch (SocketException e) {
            LOGGER.info("Error when getting host ip address: <{}>.", e.getMessage());
        }
        return null;
    }

}
