package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * 示例创建相同的path，回提示异常：
 * Exception in thread "main" org.apache.zookeeper.KeeperException$NodeExistsException: KeeperErrorCode = NodeExists for /a
 */
public class CREATE_1 {
    public static void main(String[] args) throws Exception {
        String connection_string = F.IP_1+":"+F.PORT_1;
        RetryPolicy retry = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connection_string, retry);
        client.start();
        client.create().forPath("/a", "value".getBytes());
        client.close();

    }
}
