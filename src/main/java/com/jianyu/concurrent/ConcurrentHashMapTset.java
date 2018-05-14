package com.jianyu.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ConcurrentMap
 * 
 * @author BaiJianyu <br>
 * @date 2018年5月14日下午5:45:02 <br>
 *       Better late than never. <br>
 */
public class ConcurrentHashMapTset {
	public static void main(String[] args) {
		ConcurrentMap<String,String> concurrentMap = new ConcurrentHashMap<String,String>();
		concurrentMap.put("name", "cattom");
		System.out.println(concurrentMap.get("name"));
	}
}
