package com.mm.common.utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/9
 * Time:23:26
 * Desc:描述该类的作用
 */
public class Test {

    public static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(4);

    public static void main(String[] args) {

        String myIp = "200.22.22.228";
       formatIP(myIp);
        System.out.println(formatIP(myIp));

    }



    private static String formatIP(String ip) {
        String format = ip.replaceAll("(\\d{1,3})", "00$1");
        format = format.replaceAll("0*(\\d{3})", "$1");
        return format;
    }
}
