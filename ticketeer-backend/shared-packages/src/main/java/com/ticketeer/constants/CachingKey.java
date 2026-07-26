package com.ticketeer.constants;

import lombok.Getter;

@Getter
public enum CachingKey {
    FEATURED_EVENT("events.featured"),
    CITY("events.city"),
    VENUE("events.venue"),
    ORGANIZER("events.organizer"),
    PRE_SALE("events.status:presale"),
    SOLD_OUT("events.status:soldout"),
    ENDED("events.status:ended"),
    CANCELLED("events.status:cancelled"),
    ALL_EVENTS("events.all");

    private String key;

    CachingKey(String key){
        this.key = key;
    }
}
