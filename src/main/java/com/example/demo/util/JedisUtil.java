package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Description: redi工具类
 */
@Component
public class JedisUtil {

    private static JedisPool jedisPool;

    @Autowired
    public JedisUtil(JedisPool jedisPool) {
        JedisUtil.jedisPool = jedisPool;
    }

    /**
     * 获取Jedis实例
     */
    public static Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        } else {
            return null;
        }
    }
    /**
     * 按key获取value
     */
    public static Object getValue(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return SerializationUtils.deserialize(jedis.get(key.getBytes()));
        }
    }

    /**
     * 按key设置value
     */
    public static String setValue(String key, Object value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key.getBytes(), SerializationUtils.serialize(value));
        }
    }

    /**
     * 按key设置value,expiretime(秒)
     */
    public static String setValue(String key, Object value, int expiretime) {
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.set(key.getBytes(), SerializationUtils.serialize(value));
            if (Constant.OK.equals(result)) {
                jedis.expire(key.getBytes(), expiretime);
            }
            return result;
        }
    }

    /**
     * 按key刪除
     */
    public static Long del(String... key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key);
        }
    }

    /**
     * 检查key是否存在
     */
    public static Boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key.getBytes());
        }
    }

    /**
     * 按key重新过期时间
     */
    public static Long expire(String key, int seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key.getBytes(), seconds);
        }
    }
}
