package com.ticketeer.interfaces;

import com.ticketeer.pojo.io.DeleteEventOutput;

public interface CachingRepository {
    void putString(String key, String value);
    String getString(String key);
    void putObject(String key, DeleteEventOutput value);
    DeleteEventOutput getObject(String key);
}
