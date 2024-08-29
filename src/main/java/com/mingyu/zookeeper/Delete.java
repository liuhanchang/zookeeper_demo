package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.StandardCharsets;

/**
 * 使用delete命令删除节点
 */
public class Delete {
    public static void main(String[] args) throws Exception {
        String connection_string = F.IP_1+":"+F.PORT_1;
        RetryPolicy retry = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connection_string, retry);
        client.start();

        if (client.checkExists().forPath("/newNode")!=null){
            client.delete().forPath("/newNode");
        }
        client.create().forPath("/newNode","newNodeValue".getBytes(StandardCharsets.UTF_8));

        System.out.println(new String(client.getData().forPath("/newNode")));

        client.delete().forPath("/newNode");
        System.out.println(new String(client.getData().forPath("/newNode")));

        client.close();
    }
}
