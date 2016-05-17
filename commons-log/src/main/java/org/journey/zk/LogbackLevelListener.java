package org.journey.zk;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.LoggerFactory;

/**
 * @author wudan-mac
 * @ClassName: LogbackLevelListener
 * @ClassNameExplain: 日志级别监听器
 * @Description: 当日志级别发生变化时，重新初始化logger
 * @date 2016年4月25日 下午3:43:18
 */
public class LogbackLevelListener implements IZKListener {

    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    //监听节点
    private String path;

    /**
     * @param path 节点路径
     * @Description 通过构造器注入监听者监听的节点
     * @author wudan-mac
     */
    public LogbackLevelListener(String path) {
        this.path = path;
    }

    @Override
    public void executor(CuratorFramework client) {
        //使用Curator的NodeCache来做ZNode的监听
        final NodeCache cache = new NodeCache(client, path);
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                byte[] data = cache.getCurrentData().getData();
                if (data != null) {
                    /*
                     * 切换日志级别,0trace,10debug,20info,30warn,40error
					 */
                    String level = new String(data);
                    Logger logger = (Logger) LoggerFactory.getLogger("root");
                    Level newLevel = Level.fromLocationAwareLoggerInteger(Integer.parseInt(level));
                    logger.setLevel(newLevel);
                }
            }
        });
        try {
            cache.start(true);
        } catch (Exception e) {
            log.error("Start NodeCache error for path: {}, error info: {}", path, e.getMessage());
        }
    }
}