package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 创建序列节点允许有子节点测试
 */
public class CreateSeqAndHasNode {
    public static void main(String[] args) throws Exception {
        String connectionStr = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
        client.start();

        if (client.checkExists().forPath("/b")!=null){
            client.delete().forPath("/b");
        }

        client.create().forPath("/b","".getBytes());

        System.out.println(client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/b/b","".getBytes()));
        System.out.println(client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/b/b","".getBytes()));
        System.out.println(client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/b/b","".getBytes()));


        client.create().forPath("/b/b0000000002/xyz","xyzvalue".getBytes(StandardCharsets.UTF_8));

        List<String> list = client.getChildren().forPath("/b/b0000000002");

        list.forEach(s -> System.out.println(s));
        client.close();
    }
}
