package org.journey.dao.redis.achieve.impl;

import org.journey.dao.redis.achieve.IRedisDao;
import org.journey.dao.redis.extend.JedisSentinelCommand;
import org.journey.dao.redis.extend.JedisSentinelConnectionHandler;
import org.journey.dao.redis.util.RedisConstant;
import org.journey.dao.redis.util.Reflections;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wudan-mac
 * @ClassName: RedisDaoImplShared
 * @ClassNameExplain: 分片redisDao实现类
 * @Description:
 * @date 16/5/19 下午3:38
 */
public class RedisDaoImplSentinel implements IRedisDao{

    private int maxRedirections;

    private JedisSentinelConnectionHandler connectionHandler;

    public RedisDaoImplSentinel(JedisSentinelPool jedisSentinelPool, int maxRedirections) {
        this.connectionHandler = new JedisSentinelConnectionHandler(jedisSentinelPool);
        this.maxRedirections = maxRedirections;
    }

    public void close() throws IOException {
        if (this.connectionHandler != null && this.connectionHandler.getJedisSentinelPool() != null) {
            connectionHandler.getJedisSentinelPool().destroy();
        }
    }

    @Override
    public String bset(Object bean) throws Exception {
        Map<String, Object> map = Reflections.getRedisHashFromBean(bean);
        /**
         * 执行写入
         */
        return hmset((String)map.get("key"), (Map)map.get("poMap"));
    }

    @Override
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

    @Override
    public Long bincrBy(Class clazz, String keySuffix, String field, Long value) throws Exception {

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

    @Override
    public Double bincrByFloat(Class clazz, String keySuffix, String field, Double value) throws Exception {

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

    @Override
    public String set(String key, String value) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.set(key, value);
            }
        }).run(key);
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.set(key, value, nxxx, expx, time);
            }
        }).run(key);
    }

    @Override
    public String set(String key, String value, String nxxx) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.set(key, value, nxxx);
            }
        }).run(key);
    }

    @Override
    public String get(String key) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.get(key);
            }
        }).run(key);
    }

    @Override
    public Boolean exists(String key) {
        return (Boolean) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.exists(key);
            }
        }).run(key);
    }

    @Override
    public Long persist(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.persist(key);
            }
        }).run(key);
    }

    @Override
    public String type(String key) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.type(key);
            }
        }).run(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.expire(key, seconds);
            }
        }).run(key);
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pexpire(key, milliseconds);
            }
        }).run(key);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.expireAt(key, unixTime);
            }
        }).run(key);
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pexpireAt(key, millisecondsTimestamp);
            }
        }).run(key);
    }

    @Override
    public Long ttl(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.ttl(key);
            }
        }).run(key);
    }

    @Override
    public Long pttl(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pttl(key);
            }
        }).run(key);
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return (Boolean) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.setbit(key, offset, value);
            }
        }).run(key);
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return (Boolean) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.setbit(key, offset, value);
            }
        }).run(key);
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return (Boolean) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.getbit(key, offset);
            }
        }).run(key);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.setrange(key, offset, value);
            }
        }).run(key);
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.getrange(key, startOffset, endOffset);
            }
        }).run(key);
    }

    @Override
    public String getSet(String key, String value) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.getSet(key, value);
            }
        }).run(key);
    }

    @Override
    public Long setnx(String key, String value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.setnx(key, value);
            }
        }).run(key);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.setex(key, seconds, value);
            }
        }).run(key);
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.psetex(key, milliseconds, value);
            }
        }).run(key);
    }

    @Override
    public Long decrBy(String key, long integer) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.decrBy(key, integer);
            }
        }).run(key);
    }

    @Override
    public Long decr(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.decr(key);
            }
        }).run(key);
    }

    @Override
    public Long incrBy(String key, long integer) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.incrBy(key, integer);
            }
        }).run(key);
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return (Double) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Double execute(Jedis connection) {
                return connection.incrByFloat(key, value);
            }
        }).run(key);
    }

    @Override
    public Long incr(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.incr(key);
            }
        }).run(key);
    }

    @Override
    public Long append(String key, String value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.append(key, value);
            }
        }).run(key);
    }

    @Override
    public String substr(String key, int start, int end) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.substr(key, start, end);
            }
        }).run(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hset(key, field, value);
            }
        }).run(key);
    }

    @Override
    public String hget(String key, String field) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.hget(key, field);
            }
        }).run(key);
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hsetnx(key, field, value);
            }
        }).run(key);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.hmset(key, hash);
            }
        }).run(key);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.hmget(key, fields);
            }
        }).run(key);
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hincrBy(key, field, value);
            }
        }).run(key);
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return (Double) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Double execute(Jedis connection) {
                return connection.hincrByFloat(key, field, value);
            }
        }).run(key);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return (Boolean) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.hexists(key, field);
            }
        }).run(key);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hdel(key, fields);
            }
        }).run(key);
    }

    @Override
    public Long hlen(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hlen(key);
            }
        }).run(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.hkeys(key);
            }
        }).run(key);
    }

    @Override
    public List<String> hvals(String key) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.hvals(key);
            }
        }).run(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return (Map<String, String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Map<String, String> execute(Jedis connection) {
                return connection.hgetAll(key);
            }
        }).run(key);
    }

    @Override
    public Long rpush(String key, String... strings) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.rpush(key, strings);
            }
        }).run(key);
    }

    @Override
    public Long lpush(String key, String... strings) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lpush(key, strings);
            }
        }).run(key);
    }

    @Override
    public Long llen(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.llen(key);
            }
        }).run(key);
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.lrange(key, start, end);
            }
        }).run(key);
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.ltrim(key, start, end);
            }
        }).run(key);
    }

    @Override
    public String lindex(String key, long index) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.lindex(key, index);
            }
        }).run(key);
    }

    @Override
    public String lset(String key, long index, String value) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.lset(key, index, value);
            }
        }).run(key);
    }

    @Override
    public Long lrem(String key, long index, String value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lrem(key, index, value);
            }
        }).run(key);
    }

    @Override
    public String lpop(String key) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.lpop(key);
            }
        }).run(key);
    }

    @Override
    public String rpop(String key) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.rpop(key);
            }
        }).run(key);
    }

    @Override
    public Long sadd(String key, String... members) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.sadd(key, members);
            }
        }).run(key);
    }

    @Override
    public Set<String> smembers(String key) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.smembers(key);
            }
        }).run(key);
    }

    @Override
    public Long srem(String key, String... members) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.srem(key, members);
            }
        }).run(key);
    }

    @Override
    public String spop(String key) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.spop(key);
            }
        }).run(key);
    }

    @Override
    public Set<String> spop(String key, long count) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.spop(key, count);
            }
        }).run(key);
    }

    @Override
    public Long scard(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.scard(key);
            }
        }).run(key);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return (Boolean) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.sismember(key, member);
            }
        }).run(key);
    }

    @Override
    public String srandmember(String key) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.srandmember(key);
            }
        }).run(key);
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.srandmember(key, count);
            }
        }).run(key);
    }

    @Override
    public Long strlen(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.strlen(key);
            }
        }).run(key);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, score, member);
            }
        }).run(key);
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams zAddParams) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, score, member, zAddParams);
            }
        }).run(key);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, scoreMembers);
            }
        }).run(key);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams zAddParams) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, scoreMembers, zAddParams);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrange(key, start, end);
            }
        }).run(key);
    }

    @Override
    public Long zrem(String key, String... members) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zrem(key, members);
            }
        }).run(key);
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return (Double) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Double execute(Jedis connection) {
                return connection.zincrby(key, score, member);
            }
        }).run(key);
    }

    @Override
    public Double zincrby(String key, double score, String member, ZIncrByParams zIncrByParams) {
        return (Double) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Double execute(Jedis connection) {
                return connection.zincrby(key, score, member, zIncrByParams);
            }
        }).run(key);
    }

    @Override
    public Long zrank(String key, String member) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zrank(key, member);
            }
        }).run(key);
    }

    @Override
    public Long zrevrank(String key, String member) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zrevrank(key, member);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrange(key, start, end);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeWithScores(key, start, end);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeWithScores(key, start, end);
            }
        }).run(key);
    }

    @Override
    public Long zcard(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zcard(key);
            }
        }).run(key);
    }

    @Override
    public Double zscore(String key, String member) {
        return (Double) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Double execute(Jedis connection) {
                return connection.zscore(key, member);
            }
        }).run(key);
    }

    @Override
    public List<String> sort(String key) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.sort(key);
            }
        }).run(key);
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParams) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.sort(key, sortingParams);
            }
        }).run(key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zcount(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zcount(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String min, String max) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double min, double max) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String min, String max, int offset, int count) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String min, String max) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return (Set<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByRank(key, start, end);
            }
        }).run(key);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByScore(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Long zremrangeByScore(String key, String min, String max) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByScore(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zlexcount(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByLex(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByLex(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String min, String max) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByLex(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String min, String max, int offset, int count) {
        return (Set<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByLex(key, min, max, offset, count);
            }
        }).run(key);
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByLex(key, min, max);
            }
        }).run(key);
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.linsert(key, where, pivot, value);
            }
        }).run(key);
    }

    @Override
    public Long lpushx(String key, String... strings) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lpushx(key, strings);
            }
        }).run(key);
    }

    @Override
    public Long rpushx(String key, String... strings) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.rpushx(key, strings);
            }
        }).run(key);
    }

    @Override
    @Deprecated
    public List<String> blpop(String arg) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.blpop(arg);
            }
        }).run(arg);
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.blpop(timeout, key);
            }
        }).run(key);
    }

    @Override
    @Deprecated
    public List<String> brpop(String arg) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.brpop(arg);
            }
        }).run(arg);
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.brpop(timeout, key);
            }
        }).run(key);
    }

    @Override
    public Long del(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.del(key);
            }
        }).run(key);
    }

    @Override
    public String echo(String key) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.echo(key);
            }
        }).run(key);
    }

    @Override
    public Long move(String key, int dbIndex) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.move(key, dbIndex);
            }
        }).run(key);
    }

    @Override
    public Long bitcount(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitcount(key);
            }
        }).run(key);
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitcount(key, start, end);
            }
        }).run(key);
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitpos(key, value);
            }
        }).run(key);
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams bitPosParams) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitpos(key, value, bitPosParams);
            }
        }).run(key);
    }

    @Override
    @Deprecated
    public ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {
        return (ScanResult<Map.Entry<String, String>>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<Map.Entry<String, String>> execute(Jedis connection) {
                return connection.hscan(key, cursor);
            }
        }).run(key);
    }

    @Override
    @Deprecated
    public ScanResult<String> sscan(String key, int cursor) {
        return (ScanResult<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<String> execute(Jedis connection) {
                return connection.sscan(key, cursor);
            }
        }).run(key);
    }

    @Override
    @Deprecated
    public ScanResult<Tuple> zscan(String key, int cursor) {
        return (ScanResult<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<Tuple> execute(Jedis connection) {
                return connection.zscan(key, cursor);
            }
        }).run(key);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return (ScanResult<Map.Entry<String, String>>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<Map.Entry<String, String>> execute(Jedis connection) {
                return connection.hscan(key, cursor);
            }
        }).run(key);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams scanParams) {
        return (ScanResult<Map.Entry<String, String>>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<Map.Entry<String, String>> execute(Jedis connection) {
                return connection.hscan(key, cursor, scanParams);
            }
        }).run(key);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return (ScanResult<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<String> execute(Jedis connection) {
                return connection.sscan(key, cursor);
            }
        }).run(key);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams scanParams) {
        return (ScanResult<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<String> execute(Jedis connection) {
                return connection.sscan(key, cursor, scanParams);
            }
        }).run(key);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return (ScanResult<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<Tuple> execute(Jedis connection) {
                return connection.zscan(key, cursor);
            }
        }).run(key);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams scanParams) {
        return (ScanResult<Tuple>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public ScanResult<Tuple> execute(Jedis connection) {
                return connection.zscan(key, cursor, scanParams);
            }
        }).run(key);
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pfadd(key, elements);
            }
        }).run(key);
    }

    @Override
    public long pfcount(String key) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pfcount(key);
            }
        }).run(key);
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.geoadd(key, longitude, latitude, member);
            }
        }).run(key);
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.geoadd(key, memberCoordinateMap);
            }
        }).run(key);
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return (Double) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Double execute(Jedis connection) {
                return connection.geodist(key, member1, member2);
            }
        }).run(key);
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return (Double) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Double execute(Jedis connection) {
                return connection.geodist(key, member1, member2, unit);
            }
        }).run(key);
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return (List<String>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.geohash(key, members);
            }
        }).run(key);
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return (List<GeoCoordinate>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<GeoCoordinate> execute(Jedis connection) {
                return connection.geopos(key, members);
            }
        }).run(key);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return (List<GeoRadiusResponse>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadius(key, longitude, latitude, radius, unit);
            }
        }).run(key);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return (List<GeoRadiusResponse>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadius(key, longitude, latitude, radius, unit, param);
            }
        }).run(key);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return (List<GeoRadiusResponse>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadiusByMember(key, member, radius, unit);
            }
        }).run(key);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return (List<GeoRadiusResponse>) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadiusByMember(key, member, radius, unit, param);
            }
        }).run(key);
    }

    @Override
    public String ping() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.ping();
            }
        }).run();
    }

    @Override
    public String quit() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.quit();
            }
        }).run();
    }

    @Override
    public String flushDB() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.flushDB();
            }
        }).run();
    }

    @Override
    public Long dbSize() {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.dbSize();
            }
        }).run();
    }

    @Override
    public String select(int index) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.select(index);
            }
        }).run();
    }

    @Override
    public String flushAll() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.flushAll();
            }
        }).run();
    }

    @Override
    public String auth(String password) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.auth(password);
            }
        }).run();
    }

    @Override
    public String save() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.save();
            }
        }).run();
    }

    @Override
    public String bgsave() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.bgsave();
            }
        }).run();
    }

    @Override
    public String bgrewriteaof() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.bgrewriteaof();
            }
        }).run();
    }

    @Override
    public Long lastsave() {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lastsave();
            }
        }).run();
    }

    @Override
    public String shutdown() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.shutdown();
            }
        }).run();
    }

    @Override
    public String info() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.info();
            }
        }).run();
    }

    @Override
    public String info(String section) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.info(section);
            }
        }).run();
    }

    @Override
    public String slaveof(String host, int port) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.slaveof(host, port);
            }
        }).run();
    }

    @Override
    public String slaveofNoOne() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.slaveofNoOne();
            }
        }).run();
    }

    @Override
    public Long getDB() {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.getDB();
            }
        }).run();
    }

    @Override
    public String debug(DebugParams debugParams) {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.debug(debugParams);
            }
        }).run();
    }

    @Override
    public String configResetStat() {
        return (String) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public String execute(Jedis connection) {
                return connection.configResetStat();
            }
        }).run();
    }

    @Override
    public Long waitReplicas(int replicas, long timeout) {
        return (Long) (new JedisSentinelCommand(this.connectionHandler, this.maxRedirections) {
            @Override
            public Long execute(Jedis connection) {
                return connection.waitReplicas(replicas, timeout);
            }
        }).run();
    }
}
