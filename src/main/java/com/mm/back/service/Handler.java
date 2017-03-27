package com.mm.back.service;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/3/26
 * Time:10:08
 * Desc:描述该类的作用
 */
public class Handler implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            LOGGER.info("new client info :" + socket.getInetAddress() + ":" + socket.getPort());
            //3、获取输入流，并读取客户端信息
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gbk"));
            //2输出
             writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "gbk"));
            String info = null;
            while ((info = reader.readLine()) != null) {
                LOGGER.info("client ip={} port={} content={}" , socket.getInetAddress() , socket.getPort(), info);
                writer.write(info);
                writer.flush();
            }
        } catch (Exception e) {
            LOGGER.error("Handler Exception "+ socket.getInetAddress() + ":" + socket.getPort(), e);
        } finally {
            try {
                reader.close();
                writer.close();
                LOGGER.info("close socket:" + socket.getInetAddress() + ":" + socket.getPort());
                if (socket != null) {
                    socket.close();
                }

            } catch (IOException e) {
                LOGGER.error("Handler ", e);
            }
        }
    }
}
