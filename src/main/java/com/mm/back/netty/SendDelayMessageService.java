package com.mm.back.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/9
 * Time:23:29
 * Desc:描述该类的作用
 */
@Service
public class SendDelayMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendDelayMessageService.class);
    public static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(4);
    private static final Integer DELAY = 2000;

    /**
     * 延迟发送消息 考虑客户端性能
     *
     * @param channelHandlerContext
     * @param message
     */
    public void send(ChannelHandlerContext channelHandlerContext, String message) {
        String address = channelHandlerContext.channel().remoteAddress().toString();
        LOGGER.info("SendDelayMessageService.send  address={} message={}", address, message);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                channelHandlerContext.writeAndFlush(message + "\n");
                LOGGER.info(channelHandlerContext.channel().remoteAddress() + " 发送 : " + message);
            }
        }, DELAY, TimeUnit.MILLISECONDS);
    }

}
