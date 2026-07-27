package com.ticketeer.api.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CachingService {
    List<String> getResultList(List<String> cachingKeys);
    String getResult(String cachingKey);
    void saveToCache(String cachingKey, String object);
    void saveToCache(String cachingKey, String object, Long timeToLive);
    void invalidateCache(List<String> cachingKeys);
    void invalidateCache(String cachingKey);
}
