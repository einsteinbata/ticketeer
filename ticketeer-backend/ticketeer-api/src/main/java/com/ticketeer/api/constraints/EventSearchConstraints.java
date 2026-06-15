package com.ticketeer.api.constraints;

import com.ticketeer.constants.EventStatus;
import lombok.Data;

@Data
public class EventSearchConstraints {
    private boolean isFeatured;
    private String city;
    private Long venueId;;
    private String dateRangeBeginning;
    private String dateRangeEnding;
    private Long organizerId;
    private EventStatus eventStatus;

//    public EventStatus getEventStatus() {
//        return eventStatus;
//    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = hasNoContent(eventStatus) ? null : EventStatus.valueOf(eventStatus.toUpperCase());
    }

    private static boolean hasNoContent(String str){
        return str == null || str.isEmpty();
    }

}
