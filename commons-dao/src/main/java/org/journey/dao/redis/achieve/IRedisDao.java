package org.journey.dao.redis.achieve;

import redis.clients.jedis.BasicCommands;
import redis.clients.jedis.JedisCommands;

/**
 * @author wudan-mac
 * @ClassName: IRedisDao
 * @ClassNameExplain: 对业务层公开的redisDao接口
 * 这里自定义了4个把javaBean转成map的接口
 * 同时继承 jedisCommands 对jedis操作进行了扩充
 * @Description:
 * @date 16/5/18 下午4:57
 */
public interface IRedisDao extends JedisCommands,BasicCommands {

    /**
     * @param bean java对象
     * @return java.lang.String 成功返回 OK
     * @Title: setBean
     * @TitleExplain:
     * @Description: 向redis写入一个javaBean 以map的形式存储
     * @version 1.0.0
     * @author wudan-mac
     */
    public String bset(Object bean) throws Exception;

    /**
     * @param keySuffix 要查询redis结构的key后缀
     * @param clazz     要返回的实体对象类型
     * @return T 传入的实体对象类型
     * @Title: getObject
     * @TitleExplain:
     * @Description: 获取redis中一个po实体信息
     * @version 1.0.0
     * @author wudan-mac
     */
    public <T> T bget(String keySuffix, Class<T> clazz) throws Exception;

    /**
     * @param clazz     java bean 类型
     * @param keySuffix key后缀
     * @param field     要自增的字段
     * @param value     要自增的值(增量)
     * @return Long 自增后的值
     * @Title: bincrBy
     * @TitleExplain:
     * @Description: 将redis hash map 转java 对象 取出
     * @version 1.0.0
     * @author wudan-mac
     */
    public Long bincrBy(Class clazz, String keySuffix, String field, Long value) throws Exception;


    /**
     * @param clazz     java bean 类型
     * @param keySuffix key后缀
     * @param field     要自增的字段
     * @param value     要自增的值(增量)
     * @return Double 自增后的值
     * @Title: bincrByFloat
     * @TitleExplain:
     * @Description: 将redis hash map 转java 对象 取出
     * @version 1.0.0
     * @author wudan-mac
     */
    public Double bincrByFloat(Class clazz, String keySuffix, String field, Double value) throws Exception;
}
