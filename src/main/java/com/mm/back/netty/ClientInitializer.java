package com.mm.back.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/4/3
 * Time:10:41
 * Desc:描述该类的作用
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientInitializer.class);


    public static String decoder = "utf-8";
    public static String encoder = "utf-8";
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         *
         * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
         *
         * */
            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
            pipeline.addLast("decoder", new StringDecoder(Charset.forName(decoder)));
            pipeline.addLast("encoder", new StringEncoder(Charset.forName(encoder)));
            // 客户端的逻辑
            pipeline.addLast("handler", new ClientHandler());
        }
    }

