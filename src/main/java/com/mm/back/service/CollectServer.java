package com.mm.back.service;

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

    private ServerSocket serverSocket = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        serverSocket = new ServerSocket(10086);//1024-65535的某个端口
        //service();

    }

    public void service() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (true) {
//                            Socket socket = null;
//                            try {
//                                LOGGER.info("socket run");
//                                socket = serverSocket.accept();//从连接队列中取出一个连接，如果没有则等待
//                                threadPool.submit(new Handler(socket));
//                            } catch (Exception e) {
//                                LOGGER.error("CollectServer ", e);
//                            } finally {
//                                try {
//                                    if (socket != null)
//                                        socket.close();//与一个客户端通信结束后，要关闭Socket
//                                } catch (Exception e) {
//                                    LOGGER.error("CollectServer ", e);
//                                }
//                            }
//                        }
//                    }
//                }).start();

        try {
            while (true) {
                new Thread(new Handler(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
