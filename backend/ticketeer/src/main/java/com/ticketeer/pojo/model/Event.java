package com.ticketeer.pojo.model;

import com.ticketeer.constants.EventStatus;
import lombok.Data;

@Data
public class Event {
    private Long eventId;
    private boolean isFeatured;
    private SeatArrangement seatArrangement;
    private String eventDate;
    private EventStatus eventStatus;
    private Long organizerId;
    private int maxTicketsPerPurchase;
}
