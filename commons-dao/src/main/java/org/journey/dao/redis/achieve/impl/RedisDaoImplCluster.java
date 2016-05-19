package org.journey.dao.redis.achieve.impl;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.journey.dao.redis.achieve.IRedisDao;
import org.journey.dao.redis.util.RedisConstant;
import org.journey.dao.redis.util.Reflections;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.Map;
import java.util.Set;

/**
 * @author wudan-mac
 * @ClassName: RedisDaoImpl
 * @ClassNameExplain: redisDao的集群实现
 * @Description:
 * @date 16/5/16 下午7:47
 */
public class RedisDaoImplCluster extends JedisCluster implements IRedisDao{

    public RedisDaoImplCluster(HostAndPort node) {
        super(node);
    }

    public RedisDaoImplCluster(HostAndPort node, int timeout) {
        super(node, timeout);
    }

    public RedisDaoImplCluster(HostAndPort node, int timeout, int maxRedirections) {
        super(node, timeout, maxRedirections);
    }

    public RedisDaoImplCluster(HostAndPort node, GenericObjectPoolConfig poolConfig) {
        super(node, poolConfig);
    }

    public RedisDaoImplCluster(HostAndPort node, int timeout, GenericObjectPoolConfig poolConfig) {
        super(node, timeout, poolConfig);
    }

    public RedisDaoImplCluster(HostAndPort node, int timeout, int maxRedirections, GenericObjectPoolConfig poolConfig) {
        super(node, timeout, maxRedirections, poolConfig);
    }

    public RedisDaoImplCluster(HostAndPort node, int connectionTimeout, int soTimeout, int maxRedirections, GenericObjectPoolConfig poolConfig) {
        super(node, connectionTimeout, soTimeout, maxRedirections, poolConfig);
    }

    public RedisDaoImplCluster(Set<HostAndPort> nodes) {
        super(nodes);
    }

    public RedisDaoImplCluster(Set<HostAndPort> nodes, int timeout) {
        super(nodes, timeout);
    }

    public RedisDaoImplCluster(Set<HostAndPort> nodes, int timeout, int maxRedirections) {
        super(nodes, timeout, maxRedirections);
    }

    public RedisDaoImplCluster(Set<HostAndPort> nodes, GenericObjectPoolConfig poolConfig) {
        super(nodes, poolConfig);
    }

    public RedisDaoImplCluster(Set<HostAndPort> nodes, int timeout, GenericObjectPoolConfig poolConfig) {
        super(nodes, timeout, poolConfig);
    }

    public RedisDaoImplCluster(Set<HostAndPort> jedisClusterNode, int timeout, int maxRedirections, GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, timeout, maxRedirections, poolConfig);
    }

    public RedisDaoImplCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxRedirections, GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, connectionTimeout, soTimeout, maxRedirections, poolConfig);
    }


    public String bset(Object bean) throws Exception {
        Map<String, Object> map = Reflections.getRedisHashFromBean(bean);
        /**
         * 执行写入
         */
        return hmset((String)map.get("key"), (Map)map.get("poMap"));
    }

    public <T> T bget(String keySuffix, Class<T> clazz) throws Exception {

        if (keySuffix == null || clazz == null) {
            throw new JedisDataException("get bean param cannot be null");
        }
        T result = null;
        /**
         * 拼接key
         */
        String className = clazz.getName();
        String key = className.substring(className.lastIndexOf(".") + 1, className.length());
        if (!"".equals(keySuffix)) {
            key = key + RedisConstant.REDIS_KEY_SEPARATOR + keySuffix;
        }

        //获取redis Map
        Map<String, String> map = hgetAll(key);

        return Reflections.getBeanFromRedisHash(map, clazz);

    }

    public Long bincrBy(Class clazz, String keySuffix, String field , Long value) throws Exception {

        if (keySuffix == null || clazz == null || field == null || value == null) {
            throw new JedisDataException("bincrBy bean field param cannot be null");
        }

        /**
         * 拼接key
         */
        String className = clazz.getName();
        String key = className.substring(className.lastIndexOf(".") + 1, className.length());
        if (!"".equals(keySuffix)) {
            key = key + RedisConstant.REDIS_KEY_SEPARATOR + keySuffix;
        }

        /**
         * 执行增长或减少
         */
        return hincrBy(key, field, value);
    }

    public Double bincrByFloat(Class clazz, String keySuffix, String field , Double value) throws Exception {

        if (keySuffix == null || clazz == null || field == null || value == null) {
            throw new JedisDataException("bincrBy bean field param cannot be null");
        }

        /**
         * 拼接key
         */
        String className = clazz.getName();
        String key = className.substring(className.lastIndexOf(".") + 1, className.length());
        if (!"".equals(keySuffix)) {
            key = key + RedisConstant.REDIS_KEY_SEPARATOR + keySuffix;
        }

        /**
         * 执行增长或减少
         */
        return hincrByFloat(key, field, value);
    }

}
