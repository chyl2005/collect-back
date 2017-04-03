package com.mm.back.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/4/3
 * Time:10:40
 * Desc:描述该类的作用
 */
public class NettyClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    public static String host = "127.0.0.1";
    public static int port = 10099;

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ClientInitializer());

            // 连接服务端
            Channel channel = bootstrap.connect(host, port).sync().channel();

            // 控制台输入
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String readline;
            while ((readline = in.readLine()) != null) {
                if (readline.equals("end")) {
                    break;
                }
                /*
                 * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
                 * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
                 * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
                 * */
                channel.writeAndFlush(readline + "\r\n");
                readline = in.readLine();
                in.read();
            }
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}

