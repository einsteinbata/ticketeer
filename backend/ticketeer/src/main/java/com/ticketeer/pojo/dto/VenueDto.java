package com.ticketeer.pojo.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity(name = "venue")
public class VenueDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;

    @Column(nullable = false)
    private String venueName;

    @Column(nullable = false)
    private String venueCity;

    @Column(nullable = false)
    private String venueCoordinates;

    @Column
    String managementEmail;

    @Column
    String managementPhone;

    @Column(nullable = false)
    int capacity;

}
