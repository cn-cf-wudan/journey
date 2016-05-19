package org.journey.dao.redis.extend;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisClusterMaxRedirectionsException;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @author wudan-mac
 * @ClassName: JedisSingleCommand
 * @ClassNameExplain:
 * @Description:
 * @date 16/5/18 下午6:47
 */
public abstract class JedisSingleCommand<T> {

    private JedisSingleConnectionHandler connectionHandler;

    private int redirections;

    public JedisSingleCommand(JedisSingleConnectionHandler connectionHandler, int redirections){
        this.connectionHandler = connectionHandler;
        this.redirections = redirections;
    }

    public abstract T execute(Jedis var1);

    public T run(String key) {
        if (key == null) {
            throw new JedisException("No way to dispatch this command to Redis Single.");
        } else {
            return this.runWithRetries(this.redirections);
        }
    }

    public T run() {
        return this.runWithRetries(this.redirections);
    }

    private T runWithRetries(int redirections) {
        if (redirections <= 0) {
            throw new JedisClusterMaxRedirectionsException("Too many Single redirections?");
        } else {
            Jedis connection = null;
            Object result;
            try {
                connection = this.connectionHandler.getConnection();
                result = this.execute(connection);
                return (T) result;
            } catch (JedisConnectionException e) {
                this.releaseConnection(connection);
                connection = null;
                result = this.runWithRetries(redirections - 1);
                return (T) result;
            } finally {
                this.releaseConnection(connection);
            }
        }
    }

    private void releaseConnection(Jedis connection) {
        if (connection != null) {
            connection.close();
        }

    }
}
