package org.journey.dao.redis.extend;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author wudan-mac
 * @ClassName: JedisSingleConnectionHandler
 * @ClassNameExplain: 分片redis链接处理器
 * @Description:
 * @date 16/5/18 下午6:48
 */
public class JedisSharedConnectionHandler {

    private ShardedJedisPool shardedJedisPool;

    public JedisSharedConnectionHandler(ShardedJedisPool shardedJedisPool){
        this.shardedJedisPool = shardedJedisPool;
    }

    public synchronized ShardedJedis getConnection(){
        return shardedJedisPool.getResource();
    }

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }
}
