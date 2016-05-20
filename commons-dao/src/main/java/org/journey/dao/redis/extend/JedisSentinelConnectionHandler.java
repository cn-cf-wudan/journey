package org.journey.dao.redis.extend;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author wudan-mac
 * @ClassName: JedisSingleConnectionHandler
 * @ClassNameExplain: 主从redis链接处理器
 * @Description:
 * @date 16/5/18 下午6:48
 */
public class JedisSentinelConnectionHandler {

    private JedisSentinelPool jedisSentinelPool;

    public JedisSentinelConnectionHandler(JedisSentinelPool jedisSentinelPool){
        this.jedisSentinelPool = jedisSentinelPool;
    }

    public synchronized Jedis getConnection(){
        return jedisSentinelPool.getResource();
    }

    public JedisSentinelPool getJedisSentinelPool() {
        return jedisSentinelPool;
    }
}
