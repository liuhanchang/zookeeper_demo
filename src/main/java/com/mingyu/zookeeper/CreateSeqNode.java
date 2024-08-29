package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * 使用create参数 -s 创建序列节点
 */
public class CreateSeqNode {
    public static void main(String[] args) throws Exception {
        String connectionStr = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
        client.start();

        client.create().forPath("/c","".getBytes());
        client.create().forPath("/c/c1","".getBytes());
        client.create().forPath("/c/c2","".getBytes());

        System.out.println(client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/c/my_sequential"));
        System.out.println(client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/c/my_sequential"));
        System.out.println(client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/c/my_sequential"));
        System.out.println(client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/c/my_sequential"));

        System.out.println();

        List<String> list = client.getChildren().forPath("/c");

        list.forEach(s-> System.out.println(s));

        client.close();
    }
}
