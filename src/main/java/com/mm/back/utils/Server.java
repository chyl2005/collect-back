package com.mm.back.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/3/27
 * Time:14:06
 * Desc:描述该类的作用
 */
public class Server {

    public static void main(String[] args) {
        try {
            //创建绑定到特定端口的服务器套接字
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("***服务器即将启动，等待客户端链接***");
            //侦听并接受到此套接字的连接
            Socket socket = serverSocket.accept();
            //获取输入流
            InputStream is = socket.getInputStream();
            //将字节输入流转为字符输入流
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = null;
            while ((str = br.readLine()) != null) {
                System.out.println("服务器说：客户端发送了：" + str);
                br.readLine();
            }
            //此套接字的输入流置于"流的末尾"
            socket.shutdownInput();
            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
