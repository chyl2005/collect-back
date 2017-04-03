package com.mm.back.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/4/3
 * Time:09:32
 * Desc:描述该类的作用
 */
@Service
public class NettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    @Value("${server.port}")
    private String port;

    @Autowired
    private ServerInitializer serverInitializer;

    //程序初始方法入口注解，提示spring这个程序先执行这里
    @PostConstruct
    public void serverStart() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                try {
                    ServerBootstrap serverBootstrap = new ServerBootstrap();
                    serverBootstrap.group(bossGroup, workerGroup);
                    serverBootstrap.channel(NioServerSocketChannel.class);
                    serverBootstrap.childHandler(serverInitializer);

                    // 服务器绑定端口监听
                    ChannelFuture channelFuture = serverBootstrap.bind(Integer.parseInt(port)).sync();
                    // 监听服务器关闭监听
                    channelFuture.channel().closeFuture().sync();

                    LOGGER.info("###########################################");
                    LOGGER.info("NettyServer start");

                } catch (Exception e) {
                    LOGGER.error("CollectServer ", e);
                } finally {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        }).start();

    }
}
