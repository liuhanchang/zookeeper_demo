package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.StandardCharsets;

/**
 * 使用set命令对节点设置新值
 */
public class Set {
    public static void main(String[] args) throws Exception {
        String connectionString = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);

        client.start();
        if (client.checkExists().forPath("/newNode")!=null){
            client.delete().forPath("/newNode");
        }

        client.create().forPath("/newNode","oldNodeValue".getBytes(StandardCharsets.UTF_8));

        System.out.println(new String(client.getData().forPath("/newNode")));
        client.setData().forPath("/newNode","newNodeValue".getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(client.getData().forPath("/newNode")));

        client.close();
    }


}
