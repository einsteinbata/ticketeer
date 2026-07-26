package com.ticketeer.api.repository;

import com.ticketeer.pojo.io.DeleteEventOutput;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CachingRepositoryImpl implements CachingRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public CachingRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate =  redisTemplate;
    }

    @Override
    public void putString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getString(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void putObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
