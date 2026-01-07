package com.ticketeer.pojo.model;

import lombok.Data;

@Data
public class Venue {
    private Long venueId;

    private String venueName;

    private String venueCity;

    private String venueCoordinates;

    String managementEmail;

    String managementPhone;

    int capacity;
}
