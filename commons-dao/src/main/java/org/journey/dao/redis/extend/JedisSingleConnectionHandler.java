package org.journey.dao.redis.extend;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author wudan-mac
 * @ClassName: JedisSingleConnectionHandler
 * @ClassNameExplain: 单节点redis链接处理器
 * @Description:
 * @date 16/5/18 下午6:48
 */
public class JedisSingleConnectionHandler {

    private JedisPool jedisPool;

    public JedisSingleConnectionHandler(JedisPool jedisPool){
        this.jedisPool = jedisPool;
    }

    public Jedis getConnection(){
        return jedisPool.getResource();
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
