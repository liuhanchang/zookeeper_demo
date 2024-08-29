package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * 创建临时序列节点
 */
public class CreateTempSeqNode {
    public static void main(String[] args) throws Exception {
        String connectionString = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        {
            CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);

            client.start();

            client.create().forPath("/a1","".getBytes());
            System.out.println(client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/a1/b","".getBytes()));

            System.out.println(client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/a1/b","".getBytes()));
            System.out.println(client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/a1/b","".getBytes()));

            List<String> list = client.getChildren().forPath("/a1");
            list.forEach(System.out::println);
            client.close();
        }

        System.out.println("--------------------");

        {
            CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString,retryPolicy);
            client.start();

            List<String> list = client.getChildren().forPath("/a1");
            list.forEach(System.out::println);


            client.close();
        }
    }
}
