package com.ticketeer.api.repository;


public interface CachingRepository {
    void putString(String key, String value);
    String getString(String key);
    void putObject(String key, Object value);
    Object getObject(String key);
}
