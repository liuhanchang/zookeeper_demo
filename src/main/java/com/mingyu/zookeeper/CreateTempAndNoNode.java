package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 创建临时节点不允许有子节点
 * Exception in thread "main" org.apache.zookeeper.KeeperException$NoChildrenForEphemeralsException: KeeperErrorCode = NoChildrenForEphemerals for /a1/aa
 */
public class CreateTempAndNoNode {
    public static void main(String[] args) throws Exception {
        String connectionStr = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
        client.start();
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/a1");
        client.create().forPath("/a1/aa");

        client.close();
    }
}
