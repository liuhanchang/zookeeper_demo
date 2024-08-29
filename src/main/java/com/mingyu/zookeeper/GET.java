package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * get命令查看节点对应的值。
 */
public class GET {
    public static void main(String[] args) throws Exception {
        String connectionString = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
        client.start();

        System.out.println(new String(client.getData().forPath("/mingyuroot")));
        System.out.println(new String(client.getData().forPath("/a")));
        client.close();
    }
}
