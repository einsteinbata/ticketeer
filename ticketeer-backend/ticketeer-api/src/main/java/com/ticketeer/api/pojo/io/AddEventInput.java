package com.ticketeer.api.pojo.io;

import com.ticketeer.api.pojo.model.SeatArrangement;
import lombok.Data;

@Data
public class AddEventInput extends ServiceInput {
    private SeatArrangement seatArrangement;
    private String eventDate;
    private Long venueId;
    private Long organizerId;
    private int maxTicketsPerPurchase;
}
