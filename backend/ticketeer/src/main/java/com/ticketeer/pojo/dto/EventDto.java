package com.ticketeer.pojo.dto;

import com.ticketeer.constants.EventStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "event")
public class EventDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @Column
    private boolean isFeatured;
    @Column(name = "seat_arrangement")
    private String seatArrangementJson;
    @Column
    private String eventDate;
    @Column(nullable = false)
    private EventStatus eventStatus;
    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private VenueDto venue;
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private OrganizerDto organizer;
    @Column
    private int maxTicketsPerPurchase;

}
