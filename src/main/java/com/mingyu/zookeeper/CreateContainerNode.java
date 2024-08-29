package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * 创建容器节点
 */
public class CreateContainerNode {
    public static void main(String[] args) throws Exception {
        {
            CuratorFramework client = init();

            client.create().withMode(CreateMode.CONTAINER).forPath("/a6", "".getBytes());
            client.create().forPath("/a6/b1", "".getBytes());
            client.create().forPath("/a6/b2", "".getBytes());
            client.delete().forPath("/a6/b1");
            client.delete().forPath("/a6/b2");


            {
                List<String> list = client.getChildren().forPath("/a6");
                list.forEach(System.out::println);

            }
            System.out.println("--------------");
            {
                List<String> list = client.getChildren().forPath("/");
                list.forEach(System.out::println);

            }
            close(client);

        }
        System.out.println("----------");

        Thread.sleep(60000);
        {
            String connectionStr = F.IP_1 + ":" + F.PORT_1;
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
            client.start();
            List<String> list = client.getChildren().forPath("/");
            list.forEach(System.out::println);
            client.close();

        }

    }

    public static CuratorFramework init(){
        String connectionStr = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
        client.start();

        return client;
    }

    public static void close(CuratorFramework client){
        client.close();
    }
}
