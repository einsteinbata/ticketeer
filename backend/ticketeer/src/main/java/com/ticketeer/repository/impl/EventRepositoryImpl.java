package com.ticketeer.repository.impl;

import com.ticketeer.pojo.model.Event;
import com.ticketeer.repository.EventRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @Override
    public Event saveEvent(Event event) {
        Event savedEvent = event;
        event.setEventId(44L);
        return savedEvent;
    }
}
