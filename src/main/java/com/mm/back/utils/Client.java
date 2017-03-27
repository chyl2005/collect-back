package com.mm.back.utils;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/3/27
 * Time:14:07
 * Desc:描述该类的作用
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream os = socket.getOutputStream();
            PrintWriter print = new PrintWriter(os);
            print.write("你好啊！服务器！\r\n");
            print.write("你好啊！服务器！2");
            print.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
