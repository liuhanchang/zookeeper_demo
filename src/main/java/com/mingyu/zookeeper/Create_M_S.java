package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * 创建主从节点
 */
public class Create_M_S {
    public static void main(String[] args) throws Exception {
        String connectionStr = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
        client.start();

        client.create().forPath("/aa","".getBytes());
        client.create().forPath("/aa/bb","".getBytes());
        client.create().forPath("/aa/cc","".getBytes());
        List<String> list = client.getChildren().forPath("/aa");

        list.forEach(s-> System.out.println(s));

        client.close();
    }
}
