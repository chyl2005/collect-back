package com.mm.back.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:chyl2005
 * Date:17/3/26
 * Time:10:13
 * Desc:分页
 */
public class ThreadLocalContext {

    private final static ThreadLocal<HashMap<ThreadLocalIndex, Object>> envstore =
            new ThreadLocal<HashMap<ThreadLocalIndex, Object>>() {
                @Override
                protected HashMap<ThreadLocalIndex, Object> initialValue() {
                    return new HashMap<ThreadLocalIndex, Object>();
                }
            };


    public final static <T> void set(ThreadLocalIndex key, T value) {
        envstore.get().put(key, value);
    }

    public final static <T> T get(ThreadLocalIndex key) {
        return (T) envstore.get().get(key);
    }

    public final static <F,V> void hset(ThreadLocalIndex key, F field,V value) {
        Map map = (Map) envstore.get().get(key);
        if(map==null){
            map = new HashMap();
            envstore.get().put(key,map);
        }
        map.put(field,value);
    }

    public final static <F,V> V hget(ThreadLocalIndex key, F field) {
        Map map = (Map) envstore.get().get(key);
        if(map==null){
            return null;
        }
        return (V) map.get(field);
    }


    public final static void remove(ThreadLocalIndex key) {
        envstore.get().remove(key);
    }

    public final static void clear() {
        envstore.remove();
    }

}
