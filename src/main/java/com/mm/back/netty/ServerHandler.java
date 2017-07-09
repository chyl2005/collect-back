package com.mm.back.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mm.back.constants.CommandEnum;
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

    @Autowired
    private HandlerMessageService messageService;
    /**
     * ip地址-设备硬件编号映射
     */
    public static Map<String, String> addressToDeviceNumMap = new ConcurrentHashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        //        Channel incoming = ctx.channel();
        //        for (Channel channel : channels) {
        //            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        //        }
        //设备连接时  查询设备参数
        channelHandlerContext.writeAndFlush(CommandEnum.QUERY_PARAM.getCommond() + "\n");
        //地址到 设备号映射
        addressToDeviceNumMap.put(channelHandlerContext.channel().remoteAddress().toString(), "");

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
        addressToDeviceNumMap.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        String noWhitespaceMessage = StringUtils.deleteWhitespace(message);
        //命令编号
        String address = channelHandlerContext.channel().remoteAddress().toString();
        if (message.contains("commandNum")) {
            messageService.handlerMessage(noWhitespaceMessage, address);
        }

        //发送设置信息
        if (message.contains("OK")) {
            Integer oknum = clientOKNum.get(channelHandlerContext.channel().remoteAddress().toString());
            oknum = oknum != null ? oknum % 9 : 0;

            //第一个ok 查询采集信息
            if (oknum == 0) {
                channelHandlerContext.writeAndFlush(CommandEnum.QUERY_NEW_DATA1.getCommond());
            }

            if (oknum != 0) {
                String deviceNum = addressToDeviceNumMap.get(address);
                LOGGER.info("ServerHandler.channelRead0 address={}  deviceNum={}", address, deviceNum);
                List<String> sendMessages = messageService.sendMessage(deviceNum);
                if (CollectionUtils.isNotEmpty(sendMessages)) {
                    Integer index = oknum - 1;
                    if (index < sendMessages.size()) {
                        channelHandlerContext.writeAndFlush(sendMessages.get(index));
                    }

                } else {
                    channelHandlerContext.writeAndFlush(CommandEnum.QUERY_PARAM.getCommond() + "\n");
                }
            }

            //请求ok次数
            clientOKNum.put(channelHandlerContext.channel().remoteAddress().toString(), ++oknum);
        }

        // 收到消息直接打印输出
        LOGGER.info(channelHandlerContext.channel().remoteAddress() + " Say : " + message);

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
        //super.exceptionCaught(ctx, cause);
        ctx.writeAndFlush("server error");
        LOGGER.error("ServerHandler.exceptionCaught  ", cause);

    }

    public static void main(String[] args) {
        System.out.println(1 % 9);
    }

}
