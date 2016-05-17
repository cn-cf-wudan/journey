package org.journey.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
* @ClassName: ZookeeperFactoryBean 
* @ClassNameExplain: Zookeeper
* @Description: 初始化zookeeper并注册监听器 
* @author wudan-mac
* @date 2016年4月25日 下午3:09:47 
*
 */
public class ZookeeperFactoryBean implements FactoryBean<CuratorFramework>,
		InitializingBean, DisposableBean {
	/*
	 * 使用方法：
	 * <bean id="zookeeperFactoryBean" class="com.sinldo.log.zk.ZookeeperFactoryBean" lazy-init="false">
	 *	<property name="zkConnectionString" value="127.0.0.1:2181" />
	 *	<!-- 设置zookeeper的事件监听者，logback日志级别znode监听器 -->
	 *	<property name="listeners">
	 *		<list>
	 *			<bean class="com.sinldo.log.zk.LogbackLevelListener">
	 *				<constructor-arg value="/logbacklevel" />
	 *		</bean>
	 *		</list>
	 *	</property>
	 * </bean>
	 * 
	 */
	
	private Logger logger = LoggerFactory.getLogger(ZookeeperFactoryBean.class);
	//zk客户端
	private CuratorFramework zkClient;
	//zk监听者
	private List<IZKListener> listeners;
	//zk地址
	private String zkConnectionString;
	
	
	
	@Override
	public CuratorFramework getObject() {
		return zkClient;
	}

	@Override
	public Class<?> getObjectType() {
		return CuratorFramework.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		zkClient.close();
	}

	@Override
	public void afterPropertiesSet() {
		// 1000ms 是重试间隔时间基数，3 是重试次数
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		//创建客户端
		zkClient = createWithOptions(zkConnectionString, retryPolicy, 2000,10000);
		//向zk注册观察者
		registerListeners(zkClient);
		zkClient.start();
	}
	
	

	/**
	* @Title: createWithOptions 
	* @TitleExplain: 创建zkClient
	* @Description: 创建zk客户端
	* @param connectionString zk地址
	* @param retryPolicy 重试策略
	* @param connectionTimeoutMs 连接超时时间
	* @param sessionTimeoutMs session超时时间
	* @return CuratorFramework    zkClient
	* @author wudan-mac
	 */
	private CuratorFramework createWithOptions(String connectionString,RetryPolicy retryPolicy, int connectionTimeoutMs,int sessionTimeoutMs) {
		return CuratorFrameworkFactory.builder()
				.connectString(connectionString).retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs)
				.sessionTimeoutMs(sessionTimeoutMs).build();
	}

	/**
	* @Title: registerListeners 
	* @TitleExplain: 向zk注册监听
	* @Description: 注册监听
	* @param client 
	* @author wudan-mac
	 */
	private void registerListeners(CuratorFramework client) {
		/*
		 * 注册监听者
		 */
		client.getConnectionStateListenable().addListener(
				new ConnectionStateListener() {
					@Override
					public void stateChanged(CuratorFramework client,ConnectionState newState) {//当状态发生变化时通知监听者
						logger.debug("CuratorFramework state changed: {}",newState);
						if (newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED) {
							for (IZKListener listener : listeners) {
								listener.executor(client);
								logger.debug("Listener {} executed!", listener.getClass().getName());
							}
						}
					}
				});
		/*
		 *异常处理 
		 */
		client.getUnhandledErrorListenable().addListener(
				new UnhandledErrorListener() {
					@Override
					public void unhandledError(String message, Throwable e) {
						logger.debug("CuratorFramework unhandledError: {}",message);
					}
				});
	}

	
	//getter setter
	public List<IZKListener> getListeners() {
		return listeners;
	}

	public void setListeners(List<IZKListener> listeners) {
		this.listeners = listeners;
	}

	public String getZkConnectionString() {
		return zkConnectionString;
	}

	public void setZkConnectionString(String zkConnectionString) {
		this.zkConnectionString = zkConnectionString;
	}
	
	
}