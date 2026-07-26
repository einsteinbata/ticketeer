package com.ticketeer.api.service.impl;

import com.ticketeer.api.service.CachingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CachingServiceImpl implements CachingService {

    @Value("${default.cache.ttl}")
    private String TTL;

    private final RedisTemplate<String, Object> redisTemplate;

    public CachingServiceImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<String> getResultList(List<String> cachingKeys) {
        return null;
    }

    @Override
    public String getResult(String cachingKey) {
        return (String) redisTemplate.opsForValue().get(cachingKey);
    }

    @Async
    @Override
    public void saveToCache(String cachingKey, String object) {

        redisTemplate.opsForValue().set(
                cachingKey,
                object,
                Duration.ofMinutes(Long.parseLong(TTL))
        );

    }

    @Async
    @Override
    public void saveToCache(String cachingKey, String object, Long timeToLive) {

        redisTemplate.opsForValue().set(
                cachingKey,
                object,
                Duration.ofMinutes(timeToLive)
        );

    }

    @Override
    public void invalidateCache(List<String> cachingKeys) {

    }

    @Override
    public void invalidateCache(String cachingKey) {

    }

}
