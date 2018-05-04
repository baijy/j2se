package com.jianyu.redis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis API使用演示 <br>
 * 
 * @author jianyu.bai <br>
 *         参考文章：https://www.jianshu.com/p/125357ee7651 <br>
 */
public class JedisApi {
	private static final Logger LOG = LoggerFactory.getLogger(JedisApi.class);

	private volatile static JedisApi jedisApi;

	/**
	 * 连接池
	 */
	private static Map<String, JedisPool> poolMap = new HashMap<String, JedisPool>();

	private JedisApi() {

	}

	private static JedisPool getPool(String ip, int port) {
		try {
			String key = ip + ":" + port;
			JedisPool pool = null;
			if(!poolMap.containsKey(key)){
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxIdle(RedisConfig.MAX_IDLE);
				config.setMaxTotal(8);
				//
				config.setTestOnBorrow(true);
				//
				config.setTestOnReturn(true);
				pool = new JedisPool(config, ip, port, RedisConfig.TIMEOUT);
				poolMap.put(key, pool);
				
			}else{
				poolMap.get(key);
			}
			return pool;
		} catch(Exception e){
			LOG.error("init jedis pool failed! "+e.getMessage(),e);
		}
		
		return null;
	}
	
	/**
	 * 单例模式，获取API实例
	 * @return
	 */
	public static JedisApi getRedisApi() {

        if (jedisApi == null) {
            synchronized (JedisApi.class) {
                if (jedisApi == null) {
                    jedisApi = new JedisApi();
                }
            }
        }
        return jedisApi;
    }
	
	/**
	 * 获取一个连接
	 * @param ip
	 * @param port
	 * @return
	 */
	public Jedis getRedis(String ip, int port) {
        Jedis jedis = null;
        int count = 0;
        while (jedis == null && count <= RedisConfig.RETRY_NUM) {
            try {
                jedis = getPool(ip, port).getResource();
            } catch (Exception e) {
                LOG.error("get redis failed ! " + e.getMessage(), e);
                count++;
            }
        }
        return jedis;
    }
	
	/**
	 * 释放一个连接
	 * @param jedis
	 */
	public void closeRedis(Jedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                LOG.error("colse jedis failed ! " + e.getMessage(), e);
            }
        }
    }


}
