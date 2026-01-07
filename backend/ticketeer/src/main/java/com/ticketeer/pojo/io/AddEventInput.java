package com.ticketeer.pojo.io;

import com.ticketeer.pojo.SeatArrangement;
import com.ticketeer.pojo.ServiceInput;
import lombok.Data;

@Data
public class AddEventInput extends ServiceInput {
    private SeatArrangement seatArrangement;
    private String eventDate;
    private Long venueId;
    private Long organizerId;
    private int maxTicketsPerPurchase;
}
