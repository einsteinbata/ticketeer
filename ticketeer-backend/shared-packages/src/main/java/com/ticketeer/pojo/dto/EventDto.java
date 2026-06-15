package com.ticketeer.pojo.dto;

import com.ticketeer.constants.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "event")
public class EventDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @Column
    private boolean isFeatured;
    @Column(name = "seat_arrangement")
    private String seatArrangementJson;
    @Column(nullable = false)
    private String eventDate;

    @Column(nullable = false)
    private EventStatus eventStatus;

    @Column(nullable = false)
    private Long venueId;

    @Column(nullable = false)
    private Long organizerId;
    @Column
    private int maxTicketsPerPurchase;

    public EventDto(){
        this.isFeatured = false;
        this.eventStatus = EventStatus.PRE_SALE;
    }

}
