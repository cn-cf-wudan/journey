package org.journey.dao.redis.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wudan-mac
 * @ClassName: RedisDaoInvokeHandler
 * @ClassNameExplain: redis dao 动态代理类(暂时没有用到)
 *                    这里打算用动态代理的形式维护 redis的链接 在befor和 after中 进行链接获取和释放
 *                    但是采用了 jedisCluster之后 这部分工作 已经由jedisCluster代替了
 *                    如果采用非集群方式 可以考虑这种形式
 * @Description:
 * @date 16/5/16 下午2:05
 */
public class RedisDaoInvokeHandler implements InvocationHandler {


    public <T> T getProxyInstance(Class<T> clazz) throws Exception{
        T target = clazz.newInstance();
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    private void before(){

    }

    private void after(){

    }
}
