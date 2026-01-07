package com.ticketeer.repository;

import com.ticketeer.pojo.model.Event;

public interface EventRepository {
    Event saveEvent(Event event);
}
