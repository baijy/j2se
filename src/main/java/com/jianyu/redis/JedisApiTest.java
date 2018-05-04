package com.jianyu.redis;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class JedisApiTest {
	static final String IP = "127.0.01";
	static final int PORT = 6379;
	static Jedis jedis = JedisApi.getRedisApi().getRedis(IP, PORT);
	
	private static final Logger LOG = LoggerFactory.getLogger(JedisApi.class);
	
	public static void main(String[] args) {
		//testKey();
		//testString();
		testNumber();
	}
	
	/**
	 * 测试key
	 */
	public static void testKey(){
		System.out.println("====key功能演示====");
		try {
			// 设置使用的数据库
			jedis.select(0);
	        System.out.println("清除数据：" + jedis.flushDB());
	        System.out.println("判断某个键是否存在：" + jedis.exists("1"));
	        System.out.println("新增{1，a}键值对:" + jedis.set("1", "a"));
	        System.out.println(jedis.exists("1"));
	        System.out.println("新增{2，b}键值对:" + jedis.set("2", "b"));
	        System.out.println("系统中所有的键如下：" + jedis.keys("*").toString());
	        System.out.println("删除键 1:" + jedis.del("1"));
	        System.out.println("判断键 1是否存在：" + jedis.exists("1"));
	        System.out.println("设置键 2的过期时间为5s:" + jedis.expire("2", 5));
	        TimeUnit.SECONDS.sleep(2);
	        System.out.println("查看键 2的剩余生存时间：" + jedis.ttl("2"));
	        System.out.println("移除键 2的生存时间：" + jedis.persist("2"));
	        System.out.println("查看键 2的剩余生存时间：" + jedis.ttl("2"));
	        System.out.println("查看键 2所存储的值的类型：" + jedis.type("2"));
	        System.out.println("查看键 2的值：" + jedis.get("2"));

	        System.out.println("");
		} catch (Exception e) {
			LOG.error(""+e.getMessage(),e);
		}
	}
	
	/**
	 * 测试字符串类型
	 */
	public static void testString() {
	    try {
	    	// 设置使用的数据库
	        jedis.select(1);
	        jedis.flushDB();
	        System.out.println("====字符串功能展示====");
	        System.out.println("增加:");
	        System.out.println(jedis.set("a", "1"));
	        System.out.println(jedis.set("b", "2"));
	        System.out.println(jedis.set("c", "3"));
	        System.out.println("删除键 a:" + jedis.del("a"));
	        System.out.println("获取键 a:" + jedis.get("a"));
	        System.out.println("修改键 b:" + jedis.set("b", "bChanged"));
	        System.out.println("获取键 b 的值:" + jedis.get("b"));
	        System.out.println("在键 c后面加入值：" + jedis.append("c", "End"));
	        System.out.println("获取键 c的值：" + jedis.get("c"));
	        System.out.println("增加多个键值对：" + jedis.mset("key01", "value01", "key02", "value02", "key03", "value03"));
	        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));
	        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03", "key04"));
	        System.out.println("删除多个键值对：" + jedis.del(new String[]{"key01", "key02"}));
	        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));

	        jedis.flushDB();
	        System.out.println("新增键值对防止覆盖原先值:");
	        System.out.println(jedis.setnx("key001", "value001"));
	        System.out.println(jedis.setnx("key002", "value002"));
	        System.out.println(jedis.setnx("key002", "value002-new"));
	        System.out.println("获取键key001的值：" + jedis.get("key001"));
	        System.out.println("获取键key002的值：" + jedis.get("key002"));

	        System.out.println("新增键值对并设置有效时间:");
	        System.out.println(jedis.setex("key003", 2, "value003"));
	        System.out.println("获取键key003的值：" + jedis.get("key003"));
	        TimeUnit.SECONDS.sleep(3);
	        System.out.println("获取键key003的值：" + jedis.get("key003"));

	        System.out.println("获取原值，更新为新值:");
	        System.out.println(jedis.getSet("key002", "key2GetSet"));
	        System.out.println("获取键key002的值：" + jedis.get("key002"));

	        System.out.println("截取key002的值的字符串：" + jedis.getrange("key002", 2, 5));

	        System.out.println("");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * 测试整数和浮点数
	 */
	public static void testNumber() {
	    try {
	        jedis.select(2);
	        jedis.flushDB();
	        System.out.println("====整数和浮点数功能展示====");
	        jedis.set("key001", "1");
	        jedis.set("key002", "2");
	        jedis.set("key003", "3.3");
	        System.out.println("获取键key001的值：" + jedis.get("key001"));
	        System.out.println("获取键key002的值：" + jedis.get("key002"));
	        System.out.println("将键key001的值+1：" + jedis.incr("key001"));
	        System.out.println("获取键key001的值：" + jedis.get("key001"));
	        System.out.println("将键key002的值-1：" + jedis.decr("key002"));
	        System.out.println("获取键key002的值：" + jedis.get("key002"));
	        System.out.println("将key001的值加上整数5：" + jedis.incrBy("key001", 5));
	        System.out.println("获取key001的值：" + jedis.get("key001"));
	        System.out.println("将key002的值减去整数5：" + jedis.decrBy("key002", 5));
	        System.out.println("获取key002的值：" + jedis.get("key002"));

	        System.out.println("");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
