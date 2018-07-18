package com.taotao.rest.jedis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

public class JedisTest {

    public void testJedisSingle(){
        //创建一个jedis的对象
        Jedis jedis = new Jedis("192.168.61.129",6379);
        //调用jedis对象的方法，方法名称和redis的命令一致
        jedis.set("key1","jedis test");
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭gedis。
        jedis.close();
    }

    //使用连接池
    public void testJedisPool(){
        //创建jedis连接池
        JedisPool jedisPool = new JedisPool("192.168.61.129",6379);
        //从连接池中获取Jedis对象
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
        jedisPool.close();
    }

    //连接集群
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.61.29",7001));
        nodes.add(new HostAndPort("192.168.61.29",7002));
        nodes.add(new HostAndPort("192.168.61.29",7003));
        nodes.add(new HostAndPort("192.168.61.29",7004));
        nodes.add(new HostAndPort("192.168.61.29",7005));
        nodes.add(new HostAndPort("192.168.61.29",7006));
        nodes.add(new HostAndPort("192.168.61.29",7007));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("key1","1000");
        String string = jedisCluster.get("key1");
        System.out.println(string);
        jedisCluster.close();
    }

    //单机版测试
    public void testSpringJedisSingle(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        JedisPool jedisPool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
        jedisPool.close();

    }

    //集群版测试
    public void testSpringJedisCluster(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
        String string = jedisCluster.get("key1");
        System.out.println(string);
        jedisCluster.close();
    }

    public static void main(String[] args){
        JedisTest j = new JedisTest();
        j.testSpringJedisSingle();
    }
}
