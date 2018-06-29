package com.xyang.userService.dao;

import com.xyang.userService.entity.User;
import com.xyang.userService.mapper.UserMapper;
import com.xyang.userService.utils.RedisConfigUtil;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UseDAO {

    @Inject
    UserMapper userMapper;

    private static JedisPool jedisPool;

    static{
        String host = RedisConfigUtil.get("host");
        int port = new Integer(RedisConfigUtil.get("port"));
        String password = RedisConfigUtil.get("password");
        int timeout = 2000;
        //jedis config pool
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);//连接池支持的最大并发
        config.setMaxIdle(10); //支持池中最大的空闲连接
        config.setMaxWaitMillis(1000);
        jedisPool = new JedisPool(config,host,port,timeout,password);
    }

    //todo redis连接池
    private Jedis connect() {
        String host = RedisConfigUtil.get("host");
        int port = new Integer(RedisConfigUtil.get("port"));
        String password = RedisConfigUtil.get("password");
        //jedispool


        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);
        return jedis;
    }
    //查询缓存
    //如果查询不到，查询数据库，并放入缓存
    public boolean login(String name, String pwd) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.hexists("User", name) && pwd.equals(jedis.hmget("User", name).get(0))) {
                return true;
            }

            //查询数据库，添加到缓存
            System.out.println("====name " + name + " =====pwd " + pwd);
            User user = userMapper.login(name, pwd);
            if (user != null) {
                Map<String, String> newCached = new LinkedHashMap<String, String>();
                newCached.put(name, pwd);
                jedis.hmset("User", newCached);
                return true;
            }
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //User数据库 使用注解实现
    //todo xml实现复杂的sql
    public void add(User user) {
        userMapper.add(user.getName(),user.getPassword(),user.getEmail(),user.getPhone());
    }
}

