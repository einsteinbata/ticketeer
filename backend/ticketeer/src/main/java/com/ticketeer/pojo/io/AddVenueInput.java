package com.ticketeer.pojo.io;

import lombok.Data;

@Data
public class AddVenueInput extends ServiceInput {
    private String venueName;
    private String venueCity;
    private String venueAddress;
    private String venueCoordinates;
    private String managementEmail;
    private String managementPhone;
    private int capacity;
}
