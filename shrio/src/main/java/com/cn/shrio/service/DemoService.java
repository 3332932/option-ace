package com.cn.shrio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void test(){
        redisTemplate.opsForHash().put("key","w","dfdfdf");
        Object value = redisTemplate.opsForHash().get("key","w");

        System.out.println(value);
    }
    public String getFromDataBase(){
        System.out.println("********get from database***********");
        return "hello";
    }
    public String margeFromDataBase(){
        System.out.println("########marge from database############");
        return "world";
    }


}
