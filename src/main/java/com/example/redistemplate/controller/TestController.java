package com.example.redistemplate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    @PostConstruct
    private void a() {
        test();
    }

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public String test() {
        log.info("jedis con factory {}", jedisConnectionFactory.getUsePool());
        log.info("jedis con factory {}", jedisConnectionFactory.getPort());
        log.info("jedis con factory {}", jedisConnectionFactory.getPoolConfig().getMaxIdle());
        log.info("jedis con factory {}", jedisConnectionFactory.getPoolConfig());
        log.info("jedis con factory {}", jedisConnectionFactory.getUsePool());
        log.info("jedis con factory {}", jedisConnectionFactory.getUsePool());
        return "a";
    }

    public String test_2(){

        return null;
    }


    public void saveData(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void saveList(String key, List<Object> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    public List<Object> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public void saveHash(String hashKey, String key, Object value) {
        redisTemplate.opsForHash().put(hashKey, key, value);
    }

    public Object getHash(String hashKey, String key) {
        return redisTemplate.opsForHash().get(hashKey, key);
    }

    /**
     * In this example:
     * saveData: Saves a key-value pair to Redis.
     * getData: Retrieves data from Redis based on the key.
     * saveList: Saves a list of values to a Redis list.
     * getList: Retrieves a list from Redis based on the key.
     * saveHash: Saves a key-value pair to a Redis hash.
     * getHash: Retrieves a value from a Redis hash based on the hash key and the specific key.
     */

    // redis will have different topics to discuss, like hit or miss scenarios,
    // and what kind of eviction strategy do we want to use blah blah, and also things like write back or write through
    // ae tr twy ka ho video thwr pyn kyi and also I have written some in redis 2023-08 something mhr.
    // a por ka hr ka how to use redis template and it should be enough.
}
