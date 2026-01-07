package com.ticketeer.pojo.model;

import com.ticketeer.constants.EventStatus;
import com.ticketeer.pojo.SeatArrangement;
import lombok.Data;

@Data
public class Event {

    public Event(){
        this.isFeatured = false;
        this.eventStatus = EventStatus.PRE_SALE;
    }

    private Long eventId;
    private boolean isFeatured;
    private SeatArrangement seatArrangement;
    private String eventDate;
    private EventStatus eventStatus;
    private Venue venue;
    private Organizer organizer;
    private int maxTicketsPerPurchase;

}
