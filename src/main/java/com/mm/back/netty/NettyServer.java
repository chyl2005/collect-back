package com.mm.back.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Author:chyl2005
 * Date:17/4/3
 * Time:09:32
 * Desc:描述该类的作用
 */
@Service
public class NettyServer implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    @Value("${server.port}")
    private String port;
    @Value("${server.decode}")
    private String decoder = "utf-8";
    @Value("${server.encode}")
    private String encoder = "utf-8";
    @Autowired
    private ServerHandler serverHandler;

    private ServerBootstrap serverBootstrap = new ServerBootstrap();
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private NettyServerConfig nettyServerConfig;

    @PostConstruct
    public void start() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        nettyServerConfig = new NettyServerConfig();

            serverBootstrap.group(bossGroup, workerGroup)
                           .channel(NioServerSocketChannel.class)
                           .option(ChannelOption.SO_BACKLOG, 1024)
                           //
                           .option(ChannelOption.SO_REUSEADDR, true)
                           //
                           .childOption(ChannelOption.TCP_NODELAY, true)
                           //
                           .childOption(ChannelOption.SO_SNDBUF, nettyServerConfig.getSocketSndbufSize())
                           //
                           .childOption(ChannelOption.SO_RCVBUF, nettyServerConfig.getSocketSndbufSize())
                           .localAddress(new InetSocketAddress(this.nettyServerConfig.getListenPort()))
                           .childHandler(new ChannelInitializer<SocketChannel>() {
                               @Override
                               protected void initChannel(SocketChannel ch) throws Exception {
                                   ChannelPipeline pipeline = ch.pipeline();
                                   // 以("\n")为结尾分割的 解码器
                                   pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                                           // 字符串解码 和 编码
                                           .addLast("decoder",new StringDecoder(Charset.forName(decoder)))
                                           .addLast("encoder",new StringEncoder(Charset.forName(encoder)))
                                           .addLast(serverHandler)
                                           .addLast(new NettyConnectManageHandler());
                               }

                           });

            // 服务器绑定端口监听

            LOGGER.info("###########################################");
            LOGGER.info("NettyServer start");

            try {
                ChannelFuture channelFuture =  this.serverBootstrap.bind().sync();
                // 监听服务器关闭监听
                //channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                throw new RuntimeException("this.serverBootstrap.bind().sync() InterruptedException", e);
            }





    }

    @PreDestroy
    public void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    private class NettyConnectManageHandler extends ChannelDuplexHandler {

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            SocketAddress socketAddress = ctx.channel().remoteAddress();
            LOGGER.warn("NETTY SERVER PIPELINE: exceptionCaught {}", socketAddress.toString(),cause);

            ctx.channel().close();
        }
    }
}