package com.mm.back.utils;

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
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()-startTime);
            }
        }, 2000, TimeUnit.MILLISECONDS);
    }
}
