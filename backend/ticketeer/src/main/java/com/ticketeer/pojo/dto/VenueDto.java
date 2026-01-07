package com.ticketeer.pojo.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity(name = "venue")
public class VenueDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventDto> events;

    @Column(nullable = false)
    private String venueName;

    @Column(nullable = false)
    private String venueCity;

    @Column(nullable = false, unique = true)
    private String venueCoordinates;

    @Column
    String managementEmail;

    @Column
    String managementPhone;

    @Column(nullable = false)
    int capacity;
}
