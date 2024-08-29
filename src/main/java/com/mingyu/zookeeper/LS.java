package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * 使用ls命令查看所有子节点
 */
public class LS {
    public static void main(String[] args) throws Exception {
        String connectionString = F.IP_1 +":" + F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);

        client.start();
        List<String> strings = client.getChildren().forPath("/");

        strings.forEach((String string)->{
            System.out.println(string);
        });

        System.out.println("------------");

        strings.forEach(string -> {
            System.out.println(string);
        });
        System.out.println("------------");

        strings.forEach(string -> System.out.println(string));
        System.out.println("------------");

        strings.forEach(System.out::println);

        client.close();
    }
}
