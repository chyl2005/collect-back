package com.mm.back.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/3/26
 * Time:09:49
 * Desc:数据采集
 */

@Service
@PropertySource("classpath:database.properties")
public class CollectServer implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectServer.class);

    private ExecutorService threadPool = Executors.newFixedThreadPool(20);

    private static ServerSocket serverSocket = null;

    @Override
    public void afterPropertiesSet() throws Exception {
       // serverSocket = new ServerSocket(10086);//1024-65535的某个端口
        //service();

    }

    public void service() {
        try {
            while (true) {
                new Thread(new HandlerThread(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private class HandlerThread implements Runnable {
        private Socket socket;

        public HandlerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                LOGGER.info("new client info :" + socket.getInetAddress() + ":" + socket.getPort());
                //3、获取输入流，并读取客户端信息
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gbk"));
                //2输出
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gbk"));
                String info = null;
                while ((info = reader.readLine()) != null) {
                    LOGGER.info("client ip={} port={} content={}" , socket.getInetAddress() , socket.getPort(), info);
                    writer.println(info);
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

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(10086);//1024-65535的某个端口
            new CollectServer().service();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
