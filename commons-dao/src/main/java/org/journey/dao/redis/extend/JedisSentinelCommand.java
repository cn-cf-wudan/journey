package org.journey.dao.redis.extend;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisClusterMaxRedirectionsException;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @author wudan-mac
 * @ClassName: JedisSingleCommand
 * @ClassNameExplain:
 * @Description: 主从jedis命令执行器
 * @date 16/5/18 下午6:47
 */
public abstract class JedisSentinelCommand<T> {

    //单节点链接处理器
    private JedisSentinelConnectionHandler connectionHandler;

    //链接失败时的重试次数
    private int redirections;

    public JedisSentinelCommand(JedisSentinelConnectionHandler connectionHandler, int redirections) {
        this.connectionHandler = connectionHandler;
        this.redirections = redirections;
    }

    /**
     * @param var1 redis链接
     * @return
     * @Title: execute
     * @TitleExplain:
     * @Description: redis命令执行方法
     * @version 1.0.0
     * @author wudan-mac
     */
    public abstract T execute(Jedis var1);

    /**
     * @param key
     * @return
     */
    public T run(String key) {
        if (key == null) {
            throw new JedisException("No way to dispatch this command to Redis Shared.");
        } else {
            return this.runWithRetries(this.redirections);
        }
    }

    public T run() {
        return this.runWithRetries(this.redirections);
    }

    /**
     * @param
     * @return T
     * @Title: runWithRetries
     * @TitleExplain:
     * @Description: 在重试次数内递归的 执行方法  知道重试次数小于等于0抛出异常
     * @version 1.0.0
     * @author wudan-mac
     */
    private T runWithRetries(int redirections) {

        if (redirections <= 0) {
            throw new JedisClusterMaxRedirectionsException("Too many Shared redirections?");
        } else {
            Jedis connection = null;
            Object result;
            try {
                connection = this.connectionHandler.getConnection();
                result = this.execute(connection);
                return (T) result;
            } catch (JedisConnectionException e) {

                /**
                 * 如果是链接错误 就将重试次数减1  再试一次
                 */
                this.releaseConnection(connection);
                connection = null;
                result = this.runWithRetries(redirections - 1);
                return (T) result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                this.releaseConnection(connection);
            }
        }
    }

    /**
     * @param
     * @return void
     * @Title: releaseConnection
     * @TitleExplain:
     * @Description: 释放连接资源
     * @version 1.0.0
     * @author wudan-mac
     */
    private void releaseConnection(Jedis connection) {

        if (connection != null) {
            connection.close();
        }

    }
}
