package com.mm.common.utils;

import java.text.DecimalFormat;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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

        Double depth = 23.43432;
        String sensorDepth = new DecimalFormat("000.00").format(depth.doubleValue());
        System.out.println(sensorDepth);

    }

    private static String formatIP(String ip) {
        String format = ip.replaceAll("(\\d{1,3})", "00$1");
        format = format.replaceAll("0*(\\d{3})", "$1");
        return format;
    }
}
