package com.mm.back.netty;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/4/15
 * Time:17:33
 * Desc:描述该类的作用
 */
public class NettyServerConfig {

    //监听端口
    private int listenPort = 10086;
    //
    private int serverWorkerThreads = 32;

    private int serverSelectorThreads = 3;

    private int SocketSndbufSize = 65535;

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public int getServerWorkerThreads() {
        return serverWorkerThreads;
    }

    public void setServerWorkerThreads(int serverWorkerThreads) {
        this.serverWorkerThreads = serverWorkerThreads;
    }

    public int getServerSelectorThreads() {
        return serverSelectorThreads;
    }

    public void setServerSelectorThreads(int serverSelectorThreads) {
        this.serverSelectorThreads = serverSelectorThreads;
    }

    public int getSocketSndbufSize() {
        return SocketSndbufSize;
    }

    public void setSocketSndbufSize(int socketSndbufSize) {
        SocketSndbufSize = socketSndbufSize;
    }
}
