package com.mingyu.zookeeper;

import com.mingyu.util.F;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class Create_2 {
    public static void main(String[] args) throws Exception {
        String connectionStr = F.IP_1+":"+F.PORT_1;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
        client.start();
        Stat stat = client.checkExists().forPath("/a");
        if (stat == null){
            client.create().forPath("/a","value".getBytes());
        }else{
            System.out.println("已存在路径/a,不能重复创建");
        }
        client.close();
    }
}
