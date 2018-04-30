package com.jianyu.thread.sync;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SyncMethod {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());

        Map<String,String> sMap = Collections.synchronizedMap(new HashMap<String, String>());

        ConcurrentHashMap cMap = new ConcurrentHashMap();

        //cMap.put("yoyo",null);

        System.out.println(1 << 30);


    }
}
