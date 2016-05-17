package org.journey.zk;

import org.apache.curator.framework.CuratorFramework;

/**
 * @author wudan-mac
 * @ClassName: IZKListener
 * @ClassNameExplain: ZK监听者接口
 * @Description: 当收到zk通知时需要执行的动作接口
 * @date 2016年4月25日 下午3:40:24
 */
public interface IZKListener {
    /**
     * @param client zk客户端
     * @Title: executor
     * @TitleExplain: 执行接口
     * @Description: 执行接口
     * @author wudan-mac
     */
    void executor(CuratorFramework client);
}