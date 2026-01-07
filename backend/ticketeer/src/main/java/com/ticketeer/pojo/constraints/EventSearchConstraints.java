package com.ticketeer.pojo.constraints;

import com.ticketeer.constants.EventStatus;
import com.ticketeer.pojo.model.City;
import com.ticketeer.pojo.model.Venue;
import com.ticketeer.pojo.model.Organizer;
import lombok.Data;

@Data
public class EventSearchConstraints {
    private boolean isFeatured;
    private City city;
    private Venue venue;
    private String dateRangeBeginning;
    private String dateRangeEnding;
    private Organizer organizer;
    private EventStatus eventStatus;
}
