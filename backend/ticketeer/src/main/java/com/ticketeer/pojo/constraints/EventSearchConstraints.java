package com.ticketeer.pojo.constraints;

import com.ticketeer.constants.EventStatus;
import com.ticketeer.pojo.dto.OrganizerDto;
import com.ticketeer.pojo.dto.VenueDto;
import lombok.Data;

@Data
public class EventSearchConstraints {
    private boolean isFeatured;
    private String city;
    private VenueDto venue;
    private String dateRangeBeginning;
    private String dateRangeEnding;
    private OrganizerDto organizer;
    private EventStatus eventStatus;
}
