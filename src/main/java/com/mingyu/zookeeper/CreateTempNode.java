package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * 使用create -e 创建临时节点
 */
public class CreateTempNode {
    public static void main(String[] args) throws Exception {
        String connectionStr = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        {
            CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
            client.start();

            client.create().withMode(CreateMode.EPHEMERAL).forPath("/MYEPHEMERAL","".getBytes());

            List<String> list = client.getChildren().forPath("/");
            list.forEach(s-> System.out.println(s));

            client.close();
        }
        System.out.println("--------------------");

        {
            CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
            client.start();
            List<String> list = client.getChildren().forPath("/");
            list.forEach(s-> System.out.println(s));
            client.close();
        }

    }
}
